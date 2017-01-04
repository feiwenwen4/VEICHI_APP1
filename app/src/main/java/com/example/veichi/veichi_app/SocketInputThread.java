package com.example.veichi.veichi_app;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import android.content.Intent;
import android.util.Log;
/**
 * 客户端读消息线程
 *
 *
 */
public class SocketInputThread extends Thread
{
    private boolean isStart = true;

    private static final String TAG = "SocketInputThread";

    // private MessageListener messageListener;// 消息监听接口对象

    public SocketInputThread()
    {
    }

    public void setStart(boolean isStart)
    {
        this.isStart = isStart;
    }

    @Override
    public void run()
    {
        while (isStart)
        {
            // 手机能联网，读socket数据
            if (NetManager.instance().isNetworkConnected())
            {

                if (!TCPClient.instance().isConnect())
                {
//                    CLog.e(tag, "TCPClient connet server is fail read thread sleep second" +Const.SOCKET_SLEEP_SECOND );

                    try
                    {
                        sleep(Const.SOCKET_SLEEP_SECOND * 1000);
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                readSocket();

                // 如果连接服务器失败,服务器连接失败，sleep固定的时间，能联网，就不需要sleep

//                CLog.e("socket","TCPClient.instance().isConnect() " + TCPClient.instance().isConnect() );


            }
        }
    }

    String swichByteToString(byte byteinfo){
        String mm = "";
        String hex = Integer.toHexString(byteinfo & 0xFF);
        if (hex.length() == 1) {
            mm += '0' + hex+" ";
        }else{
            mm+=hex+" ";
        }

        return mm;
    }

    public void readSocket()
    {
        Selector selector = TCPClient.instance().getSelector();
        if (selector == null)
        {
            return;
        }
        try
        {
            // 如果没有数据过来，一直柱塞
            while (selector.select() > 0)
            {
                for (SelectionKey sk : selector.selectedKeys())
                {
                    // 如果该SelectionKey对应的Channel中有可读的数据
                    if (sk.isReadable())
                    {
                        String receivedString = "";
                        String msg="";
                        String mm="";
                        StringBuilder address = new StringBuilder();
                        StringBuilder data=new StringBuilder();
                        byte[] bufferNew = new byte[32];
                        try{
                            SocketChannel sc = (SocketChannel) sk.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(32);
                            try
                            {
                                sc.read(buffer);
                                bufferNew = buffer.array();
                            } catch (IOException e)
                            {

                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                // continue;
                            }
                            buffer.flip();

                            int buf_cnt=bufferNew.length;

                            for (int i=(bufferNew.length); i>0; i--)
                            {
                                if (bufferNew[i-1]==0){
                                    buf_cnt--;
                                }else {
                                    break;
                                }
                            }

                            for (int i = 0; i < buf_cnt; i++) {
                                String hex = Integer.toHexString(bufferNew[i] & 0xFF);
                                if (hex.length() == 1) {
                                    // mm += '0' + hex+" ";
                                    mm += '0' + hex;
                                }else{
                                    // mm += hex+" ";
                                    mm += hex;
                                }
                            }
//                            Log.i(TAG, "readSocket toHexString buffer="+mm);
                            address.append(swichByteToString(bufferNew[2]));
                            address.append(swichByteToString(bufferNew[3]));
                            data.append(swichByteToString(bufferNew[4]));
                            data.append(swichByteToString(bufferNew[5]));
                            Log.i(TAG, "readSocket toHexString buffer="+data);
                        }
                        catch (Exception e){
                        }


                        receivedString = msg;

//                        CLog.e(tag, receivedString);


                        Intent i = new Intent(Const.BC);

                        i.putExtra("response", address.toString());
                        //i.putExtra("responsedata", data.toString());
                        i.putExtra("responsedata", mm);

                        ParameterActivity.s_context.sendBroadcast(i );

							/*} catch (CharacterCodingException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
                        //buffer.clear();
                        //buffer = null;

                        try
                        {
                            // 为下一次读取作准备
                            sk.interestOps(SelectionKey.OP_READ);
                            // 删除正在处理的SelectionKey
                            selector.selectedKeys().remove(sk);

                        } catch (CancelledKeyException e)
                        {
                            e.printStackTrace();
                        }


                    }
                }
            }
            // selector.close();
            // TCPClient.instance().repareRead();

        } catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ClosedSelectorException e2)
        {
        }
    }

}
