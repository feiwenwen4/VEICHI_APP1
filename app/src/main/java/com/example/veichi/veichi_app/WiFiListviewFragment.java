package com.example.veichi.veichi_app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static android.content.ContentValues.TAG;


public class WiFiListviewFragment extends Fragment {

//    private Button wifiSearchButton;
    private WifiUtils wiFilistWifiUtils;
//    private List<ScanResult> wifiResultList;
    private ArrayList<String> wifiListString = new ArrayList<String>();

    private ListView wifiSearchListView;
    private MyArrayAdater arrayWifiAdapter;

//    private String wifiPassword = null;

//    private int wifiItemId;
//    private boolean wifiItemIdConnectResult;

    public static Context s_context;

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.i("huangf","onCreateView ++++ WiFiListviewFragment");
        return inflater.inflate(R.layout.item_listview, null);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        wifiListString.clear();




//
//        wifiSearchListView = (ListView) getActivity().findViewById(R.id.wifiListView);
//        arrayWifiAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, wifiListString);
//        wifiSearchListView.setAdapter(arrayWifiAdapter);
//        ListOnItemClickListener wifiListListener = new ListOnItemClickListener();
//        wifiSearchListView.setOnItemClickListener(wifiListListener);
//
//        localWifiUtils = new WifiUtils(getActivity());
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
////        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        wifiSearchListView = (ListView) getActivity().findViewById(R.id.wifiListView);

        ListOnItemClickListener wifiListListener = new ListOnItemClickListener();
        wifiSearchListView.setOnItemClickListener(wifiListListener);

        wiFilistWifiUtils = new WifiUtils(getActivity());
        arrayWifiAdapter = new MyArrayAdater(getActivity(), android.R.layout.simple_list_item_1, wifiListString);

        //localWifiUtils = new WifiUtils(WifiListActivity.this);

    }

    public class MyArrayAdater extends ArrayAdapter<String>{

        private Context Maincontext;
        public MyArrayAdater(Context context, int textViewResourceId,ArrayList<String> aStrings) {
            super(context, textViewResourceId,aStrings);
            Maincontext = context;
            // TODO Auto-generated constructor stub
        }

        private class ViewHolder1 {
            TextView content;
        }

        private class ViewHolder2 {
            TextView title;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // position就是位置从0开始，convertView是Spinner,ListView中每一项要显示的view
            // 通常return 的view也就是convertView
            // parent就是父窗体了，也就是Spinner,ListView,GridView了.

//            convertView.setBackgroundColor(Color.WHITE);

//            ViewHolder1 contentHolder = null;
//            if (convertView == null) {
//                contentHolder = new ViewHolder1();
//                convertView.setTag(contentHolder);
//            } else {
//                contentHolder = (ViewHolder1) convertView.getTag();
//            }
//            contentHolder.content.setTextColor(Color.BLUE);//设置文本颜色
//            return convertView;


            if (null == convertView) {
                convertView = new TextView(getActivity());
                ((TextView) convertView).setTextColor(Color.BLUE);//设置文本颜色
                ((TextView) convertView).setTextSize(25f);//设置文本大小。
            }

            String data = getItem(position);
            ((TextView)convertView).setText(data);

            return convertView;
        }


            // TODO Auto-generated method stub
//            Log.i( "huangf","MyArrayAdater getView  parent.getChildCount() =  " + String.valueOf( parent.getChildCount() ));
//            for(int i = 0; i < parent.getChildCount(); i++){
//                View view = parent.getChildAt(i);
//                if (view instanceof TextView) {
//                    ((TextView) view).setTextColor(Color.BLUE);//设置文本颜色
//                    ((TextView) view).setTextSize(20f);//设置文本大小。
//                    Log.i( "huangf","MyArrayAdater getView  parent.getChildCount() =  " + i + "++++++" );
//                }
//            }
//            return super.getView(position, view111, parent);
//        }

    }

    public void setWifiListData(List<ScanResult> wifiResultList,WifiUtils setWifiUtils) {
        //int setData4wifiItemId,boolean wifiScanResult
//        List<String> wifiListString = new ArrayList<String>();
        wifiListString.clear();
//        if (wifiListString != null) {
        Log.i("huangf", "WIFIButtonListener............dataChange222222.......");
            Tools.scanResultToString(wifiResultList, wifiListString);

//        }


        wifiSearchListView.setAdapter(arrayWifiAdapter);
        arrayWifiAdapter.notifyDataSetChanged();
//        wifiItemId = setData4wifiItemId;
//        wifiItemIdConnectResult = wifiScanResult;
        wiFilistWifiUtils = setWifiUtils;
        Log.i("huangf", "WIFIButtonListener............dataChange444444.......");
    }


    class ListOnItemClickListener implements AdapterView.OnItemClickListener {
        String wifiItemSSID = null;
        private View selectedItem;
        Intent intent = new Intent();

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            // TODO Auto-generated method stub
            Log.i("ListOnItemClickListener", "start");


            Runtime run = Runtime.getRuntime();
            Process proc = null;



            selectedItem = arg1;
            arg1.setBackgroundResource(R.color.common_google_signin_btn_text_dark_default);//点击的Item项背景设置
            String wifiItem = arrayWifiAdapter.getItem(arg2);//获得选中的设备
//            Log.i("huangf", "ArrayWifiAdapter"+wifiItem);
            String[] ItemValue = wifiItem.split("--");
            wifiItemSSID = ItemValue[0];
           Log.i("huangf", "ListOnItemClickListener"+wifiItemSSID);
            int wifiItemId = wiFilistWifiUtils.IsConfiguration("\"" + wifiItemSSID + "\"");
            Log.i("huangf", "="+"ListOnItemClickListener"+String.valueOf(wifiItemId));
            if (wifiItemId != -1) {
                if (wiFilistWifiUtils.ConnectWifi(wifiItemId)) {//连接指定WIFI

                    arg1.setBackgroundResource(R.color.common_google_signin_btn_text_dark_default);


                    // intent.setClass( WifiListActivity.this,Main2Activity.class);//前面一个是一个Activity后面一个是要跳转的Activity
                    // startActivity(intent);//开始界面的跳转函数
                    // WifiListActivity.this.finish();//关闭显示的Activity
                    try {
                        Thread.sleep(800);//休眠0.5s
                        String str = "ping -c 1 -i 0.2 -W 2 " + "192.168.11.201";
                        proc = run.exec(str);
                        int result = proc.waitFor();
                        if (result == 0) {
                             Toast.makeText(getActivity(), "连接成功", Toast.LENGTH_SHORT).show();
                            ((ParameterActivity)getActivity()).ReplaceWifiList2Mointor();

//                            intent.setClass(WifiListActivity.this, Main2Activity.class);//前面一个是一个Activity后面一个是要跳转的Activity
//                            startActivity(intent);//开始界面的跳转函数
//                            //WifiListActivity.this.finish();//关闭显示的Activity
                        } else {
//                            //Toast.makeText(WifiListActivity.this, "ping测试失败", Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(), "连接失败", Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        proc.destroy();
                    }




                }
            } else {//没有配置好信息，配置
                WifiPswDialog pswDialog = new WifiPswDialog(getActivity(), new WifiPswDialog.OnCustomDialogListener() {
                    @Override
                    public void back(String str) {
                        // TODO Auto-generated method stub
                        // wifiPassword = str;
                        // wifiPassword = str;
                        // if (wifiPassword != null) {
//                        wifiPassword = "87654321";
                        int netId = wiFilistWifiUtils.AddWifiConfig(wiFilistWifiUtils.getScanResults(), wifiItemSSID, Const.WIFIPASSWORD);
                        Log.i("WifiPswDialog", String.valueOf(netId));
                        if (netId != -1) {
                            wiFilistWifiUtils.getConfiguration();//添加了配置信息，要重新得到配置信息
                            if (wiFilistWifiUtils.ConnectWifi(netId)) {
                                selectedItem.setBackgroundResource(R.color.common_google_signin_btn_text_dark_default);
                                //((ParameterActivity)getActivity()).ReplaceWifiList2Mointor();
                                wifiSearchListView.setAdapter(arrayWifiAdapter);
                                arrayWifiAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "网络连接错误", Toast.LENGTH_SHORT).show();
                            // selectedItem.setBackgroundResource(R.color.colorAccent);
                        }
                        //} else {
                        //    selectedItem.setBackgroundResource(R.color.common_plus_signin_btn_text_light);
                        //    Toast.makeText(WifiListActivity.this, "未输入有效密码", Toast.LENGTH_SHORT).show();
                        // }
                    }
                });
                pswDialog.show();
            }


        }
    }

    //实现数据传递
//    public void refreshWifilistview(Callback callback) {
//       //String msg = editText.getText().toString();
//        if (wifiListString != null) {
//            Log.i("WIFIButtonListener", "dataChange");
//            scanResultToString(wifiResultList, wifiListString);
//        }
//
//
//        callback.refreshWifilistview(wifiListString);
//

//    }
//    //创建接口
//    public interface Callback {
//        public void refreshWifilistview(List<String> msg);
//    }


}
