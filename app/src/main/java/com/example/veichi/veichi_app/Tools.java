package com.example.veichi.veichi_app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.List;


public class Tools {


    static boolean bParameterBitCmd = false;
    static boolean bParameterNodeCmd_01 = false;
    static boolean bParameterNodeCmd_02 = false;
    static boolean bParameterNodeCmd_03 = false;




//    public class AppUtil {
//
//        private AppUtil() {
//            throw new UnsupportedOperationException("cannot be instantiated");
//        }
//
//        /**
//         * 获取应用程序名称
//         */
//        public static String getAppName(Context context) {
//            try {
//                PackageManager packageManager = context.getPackageManager();
//                PackageInfo packageInfo = packageManager.getPackageInfo(
//                        context.getPackageName(), 0);
//                int labelRes = packageInfo.applicationInfo.labelRes;
//                return context.getResources().getString(labelRes);
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        /**
//         * [获取应用程序版本名称信息]
//         *
//         * @param context
//         * @return 当前应用的版本名称
//         */
//        public static String getVersionName(Context context) {
//            try {
//                PackageManager packageManager = context.getPackageManager();
//                PackageInfo packageInfo = packageManager.getPackageInfo(
//                        context.getPackageName(), 0);
//                return packageInfo.versionName;
//
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//    }

    //ScanResult类型转为String
    public static void scanResultToString(List<String> listScan, List<String> listStr,ArrayAdapter<String> arrayAdapter) {

        for (int i = 0; i < listScan.size(); i++) {
            String strScan = listScan.get(i);
            boolean bool = listStr.add(strScan);
            if (bool) {
                arrayAdapter.notifyDataSetChanged();//数据更新,只能单个Item更新，不能够整体List更新
            }
        }
    }

    public static String crc16(String str_input) {

//		int[] crc = null,data1;
//		data1=crc;
//		String str_input="01050010FF00";
        String ad=str_input.substring(0, 2);
        int[] w=new int[str_input.length()/2];
        for (int i = 0; i <str_input.length(); i+=2) {
            ad=str_input.substring(i,i+2);
            w[i/2]=Integer.parseInt(ad,16);
        }
        int[] data=w;
        int[] stem=new int[data.length+2];
        int a,b,c;
        a=0xFFFF;
        b=0xA001;
        for (int i = 0; i < data.length; i++) {
            a^=data[i];
            for (int j = 0; j < 8; j++) {
                c=(int)(a&0x01);
                a>>=1;
                if (c==1) {
                    a^=b;
                }
                System.arraycopy(data, 0, stem, 0, data.length);
                stem[stem.length-2]=(int)(a&0xFF);
                stem[stem.length-1]=(int)(a>>8);
            }
        }
        int[] z = stem;
        StringBuffer s = new StringBuffer();
        for (int j = 0; j < z.length; j++) {
            s.append(String.format("%02x", z[j]));
        }
//		System.out.print(s);

        return s.toString();


    }

    public class ChkSum {

//        @Contract(pure = true)

/**
 * 校验和
 *
 * @param msg 需要计算校验和的byte数组
 * @param length 校验和位数
 * @return 计算出的校验和数组
 */
        public byte[] SumCheck(byte[] msg, int length) {
            long mSum = 0;
            byte[] mByte = new byte[length];

            /** 逐Byte添加位数和 */
            for (byte byteMsg : msg) {
                long mNum = ((long)byteMsg >= 0) ? (long)byteMsg : ((long)byteMsg + 256);
                mSum += mNum;
            } /** end of for (byte byteMsg : msg) */

            /** 位数和转化为Byte数组 */
            for (int liv_Count = 0; liv_Count < length; liv_Count++) {
                mByte[length - liv_Count - 1] = (byte)(mSum >> (liv_Count * 8) & 0xff);
            } /** end of for (int liv_Count = 0; liv_Count < length; liv_Count++) */

            return mByte;
        }
    }


    //ScanResult类型转为String
    public static void scanResultToString(List<ScanResult> listScan, List<String> listStr) {
        Log.i("huangf", "WIFIButtonListener............dataChange333333.......");
        Log.i("huangf", "WIFIButtonListener............dataChange333333......." + String.valueOf(listScan.size()));

        for (int i = 0; i < listScan.size(); i++) {
            Log.i("huangf", "WIFIButtonListener............dataChange313131313131.......");


            ScanResult strScan = listScan.get(i);
            String str = strScan.SSID;

            if(str.startsWith("VEICHI")) {
                listStr.add(str);
                Log.i("huangf","scanResultToString str"+ str +"....cnt....=="+ String.valueOf(i));
            }

        }
    }

    public static byte[] hexStringToByteArray(String s) {
//        int len = s.length();
//        Log.i("zxj", "hexStringToByteArray s="+s);
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < (s.length() - 1); i += 2) {
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return b;
    }


    public static String sendCmdStr(String cmdStr) {
        Log.i("huangf","sendCmdStr.toString()=" + cmdStr + "............................");
        return  cmdStr;
    }

    public static String sendMonitorStr(int intNumber) {

        String strNumber = Integer.toHexString(intNumber);
        if(intNumber<16){
            StringBuilder strNumberToHex = new StringBuilder();
            strNumberToHex.append("0");
            strNumberToHex.append(strNumber);
            strNumber = strNumberToHex.toString();
        }

        StringBuilder monitorInfo = new StringBuilder();
        String strHead = "010321";
        String strData = "0001";

        monitorInfo.append(strHead);
        monitorInfo.append(strNumber);
        monitorInfo.append(strData);

        Log.i("huangf","sendMonitorStr.toString()=" + monitorInfo.toString());

        //String strSendInfo = Tools.crc16(monitorInfo.toString());

        return crc16(monitorInfo.toString());

    }

    /**
     * 正则表达式可能不严谨（写的比较唐突）
     * @param source
     * @return 返回0表示非数值，返回1表示是int型，返回2表示是double型
     */
    public static int type(String source){
        int result = 0;
        String regex1 = "^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$";
        boolean flag1 = source.matches(regex1);
        if(flag1){
            String regex2 = "^[-+]?([0-9]+)$";
            boolean flag2 = source.matches(regex2);
            if(flag2){
                result = 1;
            }
            else{
                result = 2;
            }
        }
        return result;
    }

    static boolean isDigits(String str) {//判断string中是否有小数点
        return str.matches("[-+]?[0-9]*");
    }

    static String strWriteParameter(String str){
        String strInput = str;

        if(bParameterBitCmd)return strInput;
        if(isDigits(strInput)){
            int intWriteData = Integer.parseInt(strInput);
            if(bParameterNodeCmd_01)intWriteData *=10;
            if(bParameterNodeCmd_02)intWriteData *=100;
            if(bParameterNodeCmd_03)intWriteData *=1000;
            strInput = String.valueOf(intWriteData);
            return strInput;
        }else {///判断有小数点，则将小数点去除并重新组成stringaw
            strInput=strInput.replace(".","");
            if(bParameterNodeCmd_02 && strInput.length()<3)strInput = strInput + "0";
            if(bParameterNodeCmd_03){
                if(strInput.length() == 3)strInput = strInput + "0";
                if(strInput.length() == 2)strInput = strInput + "00";
            }
            return strInput;
        }
    }

    public static String strReadParameter(int tiatle,int chkNumber) {

        String strTiatle = Integer.toHexString(tiatle);
        if(tiatle<16){
            StringBuilder strTiatleToHex = new StringBuilder();
            strTiatleToHex.append("0");
            strTiatleToHex.append(strTiatle);
            strTiatle = strTiatleToHex.toString();
        }

        String strNumber = Integer.toHexString(chkNumber);
        if(chkNumber<16){
            StringBuilder strNumberToHex = new StringBuilder();
            strNumberToHex.append("0");
            strNumberToHex.append(strNumber);
            strNumber = strNumberToHex.toString();
        }

        StringBuilder readParameterInfo = new StringBuilder();
        String strHead = "0103";
        readParameterInfo.append(strHead);
        readParameterInfo.append(strTiatle);
        readParameterInfo.append(strNumber);

        String strData = "0001";
        readParameterInfo.append(strData);

        Log.i("huangf","readParameterInfo.toString()=" + readParameterInfo.toString());

        //String strSendInfo = Tools.crc16(monitorInfo.toString());

        return crc16(readParameterInfo.toString());
    }


    public static String strWriteParameter(int tiatle,int chkNumber,String writeData) {

        String strTiatle = Integer.toHexString(tiatle);
        if(tiatle<16){
            StringBuilder strTiatleToHex = new StringBuilder();
            strTiatleToHex.append("0");
            strTiatleToHex.append(strTiatle);
            strTiatle = strTiatleToHex.toString();
        }

        String strNumber = Integer.toHexString(chkNumber);
        if(chkNumber<16){
            StringBuilder strNumberToHex = new StringBuilder();
            strNumberToHex.append("0");
            strNumberToHex.append(strNumber);
            strNumber = strNumberToHex.toString();
        }

        int intWriteData = Integer.parseInt(writeData);
        String strData = Integer.toHexString(intWriteData);
        StringBuilder strDataToHex = new StringBuilder();
        if(intWriteData<4096){
            strDataToHex.append("0");
            if(intWriteData<256){
                strDataToHex.append("0");
                if(intWriteData<16){
                    strDataToHex.append("0");
                }
            }
        }
        strDataToHex.append(strData);
        strData = strDataToHex.toString();

        StringBuilder writeParameterInfo = new StringBuilder();
        String strHead = "0106";
        writeParameterInfo.append(strHead);
        writeParameterInfo.append(strTiatle);
        writeParameterInfo.append(strNumber);
        if(bParameterBitCmd){
            writeParameterInfo.append(writeData);
        }else {
            writeParameterInfo.append(strData);
        }

        Log.i("huangf","strWriteParameter.toString()=" + writeParameterInfo.toString());

        return crc16(writeParameterInfo.toString());
    }



    private static String changeInputstrDECtoHEX(String str){

        String strChanged = Integer.toHexString(Integer.parseInt(str));
        bParameterBitCmd = true;
        return strChanged;
    }

    private static String chgStr_01_Node(String str){
        String strChanged = str;
        String strNodeFront;
        String strNodeBehind;
        StringBuilder reBuildString = new StringBuilder();
        bParameterNodeCmd_01 = true;

        if(strChanged.length()>1){    //大于1位数值 添加小数点
            strNodeFront =  strChanged.substring(0, strChanged.length()-1);
            strNodeBehind =  strChanged.substring(strChanged.length()-1, strChanged.length());
            reBuildString.append(strNodeFront);
            reBuildString.append(".");
            reBuildString.append(strNodeBehind);
            strChanged = reBuildString.toString();
            reBuildString.delete(0, reBuildString.length());
        }
        if(strChanged.length()==1){   //只有1位数值 添加小数点
            reBuildString.append("0.");
            reBuildString.append(strChanged);
            strChanged = reBuildString.toString();
            reBuildString.delete(0, reBuildString.length());
        }
        return strChanged;
    }

    private static String chgStr_02_Node(String str){
        String strChanged = str;
        String strNodeFront;
        String strNodeBehind;
        StringBuilder reBuildString = new StringBuilder();
        bParameterNodeCmd_02 = true;

        if(strChanged.length()>2){    //大于2位数值 添加小数点
            strNodeFront =  strChanged.substring(0, strChanged.length()-2);
            strNodeBehind =  strChanged.substring(strChanged.length()-2, strChanged.length());
            reBuildString.append(strNodeFront);
            reBuildString.append(".");
            reBuildString.append(strNodeBehind);
            strChanged = reBuildString.toString();
            reBuildString.delete(0, reBuildString.length());
        }else {
            if(strChanged.length()>1){    //大于1位数值 添加小数点
                reBuildString.append("0.");
                reBuildString.append(strChanged);
                strChanged = reBuildString.toString();
                reBuildString.delete(0, reBuildString.length());
            }
        }

        if(strChanged.length()==1){   //只有1位数值 添加小数点
            reBuildString.append("0.0");
            reBuildString.append(strChanged);
            strChanged = reBuildString.toString();
            reBuildString.delete(0, reBuildString.length());
        }
        return strChanged;
    }

    private static String chgStr_03_Node(String str){
        String strChanged = str;
        String strNodeFront;
        String strNodeBehind;
        StringBuilder reBuildString = new StringBuilder();
        bParameterNodeCmd_03 = true;

        if(strChanged.length()>3){    //大于3位数值 添加小数点
            strNodeFront =  strChanged.substring(0, strChanged.length()-3);
            strNodeBehind =  strChanged.substring(strChanged.length()-3, strChanged.length());
            reBuildString.append(strNodeFront);
            reBuildString.append(".");
            reBuildString.append(strNodeBehind);
            strChanged = reBuildString.toString();
            reBuildString.delete(0, reBuildString.length());
        }else {
            if(strChanged.length()>2){    //大于2位数值 添加小数点
                reBuildString.append("0.");
                reBuildString.append(strChanged);
                strChanged = reBuildString.toString();
                reBuildString.delete(0, reBuildString.length());
            }else {
                if(strChanged.length()>1){    //大于1位数值 添加小数点
                    reBuildString.append("0.0");
                    reBuildString.append(strChanged);
                    strChanged = reBuildString.toString();
                    reBuildString.delete(0, reBuildString.length());
                }
            }
        }
        if(strChanged.length()==1){   //只有1位数值 添加小数点
            reBuildString.append("0.00");
            reBuildString.append(strChanged);
            strChanged = reBuildString.toString();
            reBuildString.delete(0, reBuildString.length());
        }
        return strChanged;
    }
    private static String chgStr_00_Node(String str){
        String strInput = str;
        return strInput;
    }

    private static String strInputChg(String str, String unit){
        String strInput = str;
        StringBuilder reBuildString = new StringBuilder();

        reBuildString.append(strInput);
        reBuildString.append(unit);
        strInput = reBuildString.toString();

        return strInput;

    }

    public static String strParaGroupReadDeal(String str,int chknumber) {
        String strInput = str;
        bParameterBitCmd = false;
        bParameterNodeCmd_01 = false;
        bParameterNodeCmd_02 = false;
        bParameterNodeCmd_03 = false;

        switch (chknumber){

            case Const.C_00:  strInput = strInputChg(chgStr_01_Node(strInput)," kg/cm^2"); //小数点 1    单位  kg/cm^2
                break;
            case Const.C_01:  strInput = strInputChg(chgStr_01_Node(strInput)," kg/cm^2"); //小数点 1    单位  kg/cm^2
                break;
            case Const.C_02:  strInput = strInputChg(chgStr_00_Node(strInput)," rpm"); //小数点 0    单位  rpm
                break;
            case Const.C_03:  strInput = strInputChg(chgStr_00_Node(strInput)," rpm"); //小数点 0    单位  rpm
                break;
            case Const.C_04:  strInput = strInputChg(chgStr_00_Node(strInput)," rpm"); //小数点 0    单位  rpm
                break;
            case Const.C_05:  strInput = strInputChg(chgStr_01_Node(strInput)," A"); //小数点 1    单位  A
                break;
            case Const.C_06:  strInput = strInputChg(chgStr_01_Node(strInput)," V");//小数点 1    单位  V
                break;
            case Const.C_07:  strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.C_08:  strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.C_09:  strInput = strInputChg(chgStr_01_Node(strInput)," NM"); //小数点 1    单位  NM
                break;
            case Const.C_10:  strInput = strInputChg(chgStr_01_Node(strInput)," KW"); //小数点 1    单位  KW
                break;
            case Const.C_11:  strInput = strInputChg(chgStr_01_Node(strInput)," V");//小数点 1    单位  V
                break;
            case Const.C_12:  strInput = strInputChg(chgStr_01_Node(strInput)," ℃"); //小数点 1    单位  ℃
                break;
            case Const.C_13:  strInput = strInputChg(chgStr_01_Node(strInput)," ℃"); //小数点 1    单位  ℃
                break;
            case Const.C_14:  strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.C_15:  strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.C_16:  strInput = strInputChg(chgStr_00_Node(strInput)," V/mA"); //小数点 0    单位  V/mA
                break;
            case Const.C_17:  strInput = strInputChg(chgStr_00_Node(strInput)," V/mA"); //小数点 0    单位  V/mA
                break;
            case Const.C_18:  strInput = strInputChg(chgStr_00_Node(strInput)," V/mA"); //小数点 0    单位  V/mA
                break;
            case Const.C_19:  strInput = strInputChg(chgStr_00_Node(strInput)," mV"); //小数点 0    单位  mV
                break;
            case Const.C_20:  strInput = strInputChg(chgStr_00_Node(strInput)," V/mA"); //小数点 0    单位  V/mA
                break;
            case Const.C_21:  strInput = strInputChg(chgStr_00_Node(strInput)," V/mA"); //小数点 0    单位  V/mA
                break;
            case Const.C_22:  strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.C_23:  strInput = strInputChg(chgStr_01_Node(strInput)," 小时"); //小数点 1    单位  小时
                break;
            case Const.C_24:  strInput = strInputChg(chgStr_00_Node(strInput)," 小时"); //小数点 0    单位  小时
                break;
            case Const.C_25:  strInput = strInputChg(chgStr_00_Node(strInput)," kW"); //小数点 0    单位  kW
                break;
            case Const.C_26:  strInput = strInputChg(chgStr_00_Node(strInput)," V"); //小数点 0    单位  V
                break;
            case Const.C_27:  strInput = strInputChg(chgStr_00_Node(strInput)," A"); //小数点 0    单位  A
                break;
            case Const.C_28:  strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.C_29:  strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;


            case Const.F0_00: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_01: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_02: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_03: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_04: strInput = strInputChg(chgStr_03_Node(strInput),""); //小数点 3   单位  无
                break;
            case Const.F0_05: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_06: strInput = strInputChg(chgStr_03_Node(strInput),""); //小数点 3   单位  无
                break;
            case Const.F0_07: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F0_08: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F0_09: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F0_10: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_11: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F0_12: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F0_13: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_14: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F0_15: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F0_16: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_17: strInput = strInputChg(chgStr_02_Node(strInput)," kHz"); //小数点 1    单位  kHz
                break;
            case Const.F0_18: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F0_19: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_20: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_21: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_22: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_23: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_24: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F0_25: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_26: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F0_27: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F0_28: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F0_29: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F0_30: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;



            case Const.F1_00: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F1_01: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_02: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F1_03: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F1_04: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F1_05: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F1_06: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_07: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F1_08: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F1_09: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F1_10: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F1_11: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F1_12: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F1_13: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F1_14: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F1_15: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F1_16: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F1_17: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F1_18: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_19: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_20: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_21: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_22: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_23: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_24: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_25: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F1_26: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F1_27: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F1_28: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F1_29: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F1_30: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_31: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F1_32: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F1_33: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_34: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F1_35: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F1_36: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;



            case Const.F2_00: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_01: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_02: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_03: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_04: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_05: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_06: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_07: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_08: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F2_09: strInput = strInputChg(chgStr_03_Node(strInput)," sec"); //小数点 3   单位  sec
                break;
            case Const.F2_10: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F2_11: strInput = strInputChg(chgStr_03_Node(strInput)," sec"); //小数点 3   单位  sec
                break;
            case Const.F2_12: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_13: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F2_14: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_15: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_16: strInput = strInputChg(chgStr_02_Node(strInput)," kHz"); //小数点 2    单位  kHz
                break;
            case Const.F2_17: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2   单位  %
                break;
            case Const.F2_18: strInput = strInputChg(chgStr_02_Node(strInput)," kHz"); //小数点 2    单位  kHz
                break;
            case Const.F2_19: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2   单位  %
                break;
            case Const.F2_20: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F2_21: strInput = strInputChg(chgStr_03_Node(strInput)," kHz"); //小数点 3    单位  kHz
                break;
            case Const.F2_22: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_23: strInput = strInputChg(chgStr_02_Node(strInput)," Hz/s"); //小数点 2    单位  Hz/s
                break;
            case Const.F2_24: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_25: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_26: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_27: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_28: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_29: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_30: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_31: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_32: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F2_33: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F2_34: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F2_35: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F2_36: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F2_37: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F2_38: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_39: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F2_40: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_41: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_42: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_43: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_44: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_45: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_46: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_47: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_48: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_49: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_50: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_51: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_52: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_53: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_54: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_55: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F2_56: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F2_57: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_58: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_59: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_60: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F2_61: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_62: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_63: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_64: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_65: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_66: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_67: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_68: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  SEC
                break;
            case Const.F2_69: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_70: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_71: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_72: strInput = strInputChg(chgStr_03_Node(strInput)," sec"); //小数点 3   单位  sec
                break;
            case Const.F2_73: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F2_74: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F2_75: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;


            case Const.F3_00: strInput = strInputChg(chgStr_02_Node(strInput)," V");//小数点 2    单位  V
                break;
            case Const.F3_01: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2   单位  %
                break;
            case Const.F3_02: strInput = strInputChg(chgStr_02_Node(strInput)," V");//小数点 2    单位  V
                break;
            case Const.F3_03: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2   单位  %
                break;
            case Const.F3_04: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F3_05: strInput = strInputChg(chgStr_02_Node(strInput)," V");//小数点 2    单位  V
                break;
            case Const.F3_06: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2   单位  %
                break;
            case Const.F3_07: strInput = strInputChg(chgStr_02_Node(strInput)," V");//小数点 2    单位  V
                break;
            case Const.F3_08: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2   单位  %
                break;
            case Const.F3_09: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F3_10: strInput = strInputChg(chgStr_02_Node(strInput)," mA"); //小数点 2    单位  mA
                break;
            case Const.F3_11: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2   单位  %
                break;
            case Const.F3_12: strInput = strInputChg(chgStr_02_Node(strInput)," mA"); //小数点 2    单位  mA
                break;
            case Const.F3_13: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2   单位  %
                break;
            case Const.F3_14: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.F3_15: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F3_16: strInput = strInputChg(chgStr_02_Node(strInput)," mA"); //小数点 2    单位  mA
                break;
            case Const.F3_17: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2   单位  %
                break;
            case Const.F3_18: strInput = strInputChg(chgStr_02_Node(strInput)," mA"); //小数点 2    单位  mA
                break;
            case Const.F3_19: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2   单位  %
                break;
            case Const.F3_20: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F3_21: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F3_22: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F3_23: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F3_24: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F3_25: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F3_27: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F3_28: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.F3_29: strInput = strInputChg(chgStr_02_Node(strInput)," kHz"); //小数点 2    单位   kHz
                break;
            case Const.F3_30: strInput = strInputChg(chgStr_02_Node(strInput)," kHz"); //小数点 2    单位   kHz
                break;
            case Const.F3_31: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F3_32: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F3_33: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F3_34: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F3_35: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F3_36: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F3_37: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F3_38: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F3_39: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F3_40: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F3_41: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F3_42: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F3_43: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F3_44: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F3_45: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F3_46: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F3_47: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F3_48: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F3_49: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F3_50: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F3_51: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F3_52: strInput = strInputChg(chgStr_02_Node(strInput),"  sec"); //小数点 2    单位   sec
                break;


            case Const.F4_00: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F4_01: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F4_02: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F4_03: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F4_04: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F4_05: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F4_06: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F4_07: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F4_08: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F4_09: strInput = strInputChg(chgStr_02_Node(strInput)," V"); //小数点 2    单位  V
                break;
            case Const.F4_10: strInput = strInputChg(chgStr_02_Node(strInput)," %"); //小数点 2    单位  %
                break;
            case Const.F4_11: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F4_12: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F4_13: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F4_14: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F4_15: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F4_16: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;


            case Const.F5_00: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F5_01: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F5_02: strInput = strInputChg(chgStr_01_Node(strInput)," kW"); //小数点 1   单位   kW
                break;
            case Const.F5_03: strInput = strInputChg(chgStr_02_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F5_04: strInput = strInputChg(chgStr_00_Node(strInput)," rpm"); //小数点 0    单位  无
                break;
            case Const.F5_05: strInput = strInputChg(chgStr_00_Node(strInput)," V"); //小数点 0    单位  V
                break;
            case Const.F5_06: strInput = strInputChg(chgStr_01_Node(strInput)," A"); //小数点 1    单位  A
                break;
            case Const.F5_07: strInput = strInputChg(chgStr_01_Node(strInput)," A"); //小数点 1    单位  A
                break;
            case Const.F5_08: strInput = strInputChg(chgStr_03_Node(strInput),""); //小数点 3    单位  无
                break;
            case Const.F5_09: strInput = strInputChg(chgStr_03_Node(strInput),""); //小数点 3    单位  无
                break;
            case Const.F5_10: strInput = strInputChg(chgStr_01_Node(strInput)," mH"); //小数点 1    单位   mH
                break;
            case Const.F5_11: strInput = strInputChg(chgStr_01_Node(strInput)," mH"); //小数点 1    单位   mH
                break;
            case Const.F5_12: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F5_13: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F5_14: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F5_15: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F5_16: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F5_17: strInput = strInputChg(chgStr_03_Node(strInput)," sec"); //小数点 0    单位   sec
                break;
            case Const.F5_18: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F5_19: strInput = strInputChg(chgStr_03_Node(strInput),""); //小数点 3    单位  无
                break;
            case Const.F5_20: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F5_21: strInput = strInputChg(chgStr_03_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F5_22: strInput = strInputChg(chgStr_02_Node(strInput)," mH"); //小数点 0    单位   mH
                break;
            case Const.F5_23: strInput = strInputChg(chgStr_02_Node(strInput)," mH"); //小数点 0    单位   mH
                break;
            case Const.F5_24: strInput = strInputChg(chgStr_01_Node(strInput)," v"); //小数点 1    单位   v
                break;
            case Const.F5_25: strInput = strInputChg(chgStr_01_Node(strInput)," °"); //小数点 1    单位   °
                break;
            case Const.F5_26: strInput = strInputChg(chgStr_01_Node(strInput)," Hz"); //小数点 1    单位  Hz
                break;
            case Const.F5_27: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F5_28: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;


            case Const.F6_00: strInput = strInputChg(chgStr_02_Node(strInput),""); //小数点 2    单位  无
                break;
            case Const.F6_01: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位   sec
                break;
            case Const.F6_02: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_03: strInput = strInputChg(chgStr_03_Node(strInput)," sec"); //小数点 3    单位   sec
                break;
            case Const.F6_04: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位   Hz
                break;
            case Const.F6_05: strInput = strInputChg(chgStr_02_Node(strInput),""); //小数点 2    单位  无
                break;
            case Const.F6_06: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位   sec
                break;
            case Const.F6_07: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位   sec
                break;
            case Const.F6_08: strInput = strInputChg(chgStr_03_Node(strInput)," sec"); //小数点 3    单位   sec
                break;
            case Const.F6_09: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位   Hz
                break;
            case Const.F6_10: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F6_11: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F6_12: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F6_13: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F6_14: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F6_15: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F6_16: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 1    单位  无
                break;
            case Const.F6_17: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 1    单位  无
                break;
            case Const.F6_18: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 1    单位  无
                break;
            case Const.F6_19: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 1    单位  无
                break;
            case Const.F6_20: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_21: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_22: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_23: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_24: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_25: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F6_26: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  ms
                break;
            case Const.F6_27: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F6_28: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  ms
                break;
            case Const.F6_29: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_30: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_31: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F6_32: strInput = strInputChg(chgStr_01_Node(strInput)," ms"); //小数点 1    单位  ms
                break;
            case Const.F6_33: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_34: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_35: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_36: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F6_37: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F6_38: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_39: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_40: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_41: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_42: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_43: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_44: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_45: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F6_46: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  ms
                break;
            case Const.F6_47: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F6_48: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F6_49: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_50: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 1    单位  无
                break;
            case Const.F6_51: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F6_52: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_53: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F6_54: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F6_55: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F6_56: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  ms
                break;
            case Const.F6_57: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  ms
                break;


            case Const.F7_00: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F7_01: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F7_02: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.F7_03: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F7_04: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F7_05: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F7_06: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F7_07: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F7_08: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F7_09: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  sec
                break;
            case Const.F7_10: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  sec
                break;
            case Const.F7_11: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  sec
                break;
            case Const.F7_12: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  sec
                break;


            case Const.F8_00: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F8_01: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F8_02: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F8_03: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F8_04: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F8_05: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F8_06: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F8_07: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F8_08: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F8_09: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F8_10: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F8_11: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F8_12: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F8_13: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F8_14: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F8_15: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.F8_16: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 1    单位  无
                break;
            case Const.F8_17: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  ms
                break;
            case Const.F8_18: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F8_19: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位  %
                break;
            case Const.F8_20: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  ms
                break;
            case Const.F8_21: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F8_22: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F8_23: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.F8_24: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.F8_25: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 0    单位   sec
                break;
            case Const.F8_26: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;


            case Const.FA_00: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位   %
                break;
            case Const.FA_01: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位   %
                break;
            case Const.FA_02: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位   ms
                break;
            case Const.FA_03: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_04: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_05: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_06: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_07: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位   %
                break;
            case Const.FA_08: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.FA_09: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_10: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.FA_11: strInput = strInputChg(chgStr_00_Node(strInput)," %"); //小数点 0    单位   %
                break;
            case Const.FA_12: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_13: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_14: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_15: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_16: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_17: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FA_18: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_19: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FA_20: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.FA_21: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.FA_22: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_23: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  sec
                break;
            case Const.FA_24: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_25: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_26: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FA_27: strInput = strInputChg(chgStr_00_Node(strInput)," V"); //小数点 0    单位  V
                break;
            case Const.FA_28: strInput = strInputChg(chgStr_01_Node(strInput)," A"); //小数点 0    单位  A
                break;
            case Const.FA_29: strInput = strInputChg(chgStr_00_Node(strInput)," V"); //小数点 0    单位  V
                break;
            case Const.FA_30: strInput = strInputChg(chgStr_00_Node(strInput)," ℃"); //小数点 0    单位  ℃
                break;
            case Const.FA_31: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FA_32: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FA_33: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FA_34: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_35: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FA_36: strInput = strInputChg(chgStr_00_Node(strInput)," V"); //小数点 0    单位  V
                break;
            case Const.FA_37: strInput = strInputChg(chgStr_01_Node(strInput)," A"); //小数点 0    单位  A
                break;
            case Const.FA_38: strInput = strInputChg(chgStr_00_Node(strInput)," V"); //小数点 0    单位  V
                break;
            case Const.FA_39: strInput = strInputChg(chgStr_00_Node(strInput)," ℃"); //小数点 0    单位  ℃
                break;
            case Const.FA_40: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FA_41: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FA_42: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FA_43: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FA_44: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;



            case Const.FB_00: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FB_01: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.FB_02: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FB_03: strInput = strInputChg(chgStr_02_Node(strInput),""); //小数点 2    单位  无
                break;
            case Const.FB_04: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 1    单位  无
                break;
            case Const.FB_05: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FB_06: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.FB_07: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  sec
                break;
            case Const.FB_08: strInput = strInputChg(chgStr_02_Node(strInput),""); //小数点 2    单位  无
                break;
            case Const.FB_09: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  sec
                break;
            case Const.FB_10: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  sec
                break;
            case Const.FB_11: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  sec
                break;
            case Const.FB_12: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.FB_13: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FB_14: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  sec
                break;
            case Const.FB_15: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FB_16: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.FB_17: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;


            case Const.FC_00: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_01: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_02: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_03: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_04: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_05: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_06: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_07: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_08: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_09: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_10: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_11: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_12: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_13: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_14: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_15: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_16: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_17: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_18: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_19: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_20: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_21: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_22: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_23: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_24: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_25: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_26: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_27: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_28: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_29: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_30: strInput = strInputChg(chgStr_01_Node(strInput)," (s/m/h)"); //小数点 1    单位  (s/m/h)
                break;
            case Const.FC_31: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_32: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_33: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_34: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_35: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_36: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_37: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_38: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_39: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_40: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_41: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_42: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_43: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_44: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_45: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_46: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FC_47: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FC_48: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FC_49: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FC_50: strInput = strInputChg(chgStr_02_Node(strInput)," Hz"); //小数点 2    单位  Hz
                break;
            case Const.FC_51: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  sec
                break;
            case Const.FC_52: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.FC_53: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1    单位  %
                break;
            case Const.FC_54: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  sec
                break;
            case Const.FC_55: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  sec
                break;



            case Const.FD_00: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FD_01: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FD_02: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FD_03: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FD_04: strInput = strInputChg(chgStr_02_Node(strInput),""); //小数点 2    单位  无
                break;
            case Const.FD_05: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  ms
                break;
            case Const.FD_06: strInput = strInputChg(chgStr_01_Node(strInput)," sec"); //小数点 1    单位  sec
                break;
            case Const.FD_07: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FD_08: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FD_09: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FD_10: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FD_11: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FD_12: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FD_13: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;


            case Const.FE_00: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FE_01: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FE_02: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FE_03: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FE_04: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FE_05: strInput = strInputChg(chgStr_01_Node(strInput)," kg/cm^2"); //小数点 1    单位  kg/cm^2
                break;
            case Const.FE_06: strInput = strInputChg(chgStr_01_Node(strInput)," kg/cm^2"); //小数点 1    单位  kg/cm^2
                break;
            case Const.FE_07: strInput = strInputChg(chgStr_00_Node(strInput)," rpm"); //小数点 0    单位  rpm
                break;
            case Const.FE_08: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FE_09: strInput = strInputChg(chgStr_01_Node(strInput)," kg/cm^2"); //小数点 1    单位  kg/cm^2
                break;
            case Const.FE_10: strInput = strInputChg(chgStr_00_Node(strInput)," rpm"); //小数点 0    单位  rpm
                break;
            case Const.FE_11: strInput = strInputChg(chgStr_00_Node(strInput)," rpm"); //小数点 0    单位  rpm
                break;
            case Const.FE_12: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FE_13: strInput = strInputChg(chgStr_01_Node(strInput)," kg/cm^2"); //小数点 1    单位  kg/cm^2
                break;
            case Const.FE_14: strInput = strInputChg(chgStr_00_Node(strInput)," rpm"); //小数点 0    单位  rpm
                break;
            case Const.FE_15: strInput = strInputChg(chgStr_02_Node(strInput)," sec"); //小数点 2    单位  SEC
                break;
            case Const.FE_16: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  MS
                break;
            case Const.FE_17: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  MS
                break;
            case Const.FE_18: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  MS
                break;
            case Const.FE_19: strInput = strInputChg(chgStr_00_Node(strInput)," ms"); //小数点 0    单位  MS
                break;
            case Const.FE_20: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FE_21: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.FE_22: strInput = strInputChg(chgStr_01_Node(strInput)," ms"); //小数点 1    单位  MS
                break;
            case Const.FE_23: strInput = strInputChg(chgStr_01_Node(strInput)," ms"); //小数点 1    单位  MS
                break;
            case Const.FE_24: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.FE_25: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 1   单位  无
                break;
            case Const.FE_26: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.FE_27: strInput = strInputChg(chgStr_01_Node(strInput)," ms"); //小数点 1    单位  MS
                break;
            case Const.FE_28: strInput = strInputChg(chgStr_01_Node(strInput)," ms"); //小数点 1    单位  MS
                break;
            case Const.FE_29: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.FE_30: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 1   单位  无
                break;
            case Const.FE_31: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.FE_32: strInput = strInputChg(chgStr_01_Node(strInput)," ms"); //小数点 1    单位  MS
                break;
            case Const.FE_33: strInput = strInputChg(chgStr_01_Node(strInput)," ms"); //小数点 1    单位  MS
                break;
            case Const.FE_34: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.FE_35: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 1   单位  无
                break;
            case Const.FE_36: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.FE_37: strInput = strInputChg(chgStr_01_Node(strInput)," ms"); //小数点 1    单位  MS
                break;
            case Const.FE_38: strInput = strInputChg(chgStr_01_Node(strInput)," ms"); //小数点 1    单位  MS
                break;
            case Const.FE_39: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.FE_40: strInput = strInputChg(chgStr_01_Node(strInput),""); //小数点 1   单位  无
                break;
            case Const.FE_41: strInput = changeInputstrDECtoHEX(str) + " (位操作)";     //  转换为HEX显示   4位
                break;
            case Const.FE_42: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.FE_43: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.FE_44: strInput = strInputChg(chgStr_01_Node(strInput)," %"); //小数点 1   单位  %
                break;
            case Const.FE_45: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FE_46: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;
            case Const.FE_47: strInput = strInputChg(chgStr_00_Node(strInput)," rpm"); //小数点 0    单位  rpm
                break;
            case Const.FE_48: strInput = strInputChg(chgStr_00_Node(strInput)," rpm"); //小数点 0    单位  rpm
                break;
            case Const.FE_49: strInput = strInputChg(chgStr_00_Node(strInput),""); //小数点 0    单位  无
                break;





            default:
                break;
        }
        return strInput;
    }

}
