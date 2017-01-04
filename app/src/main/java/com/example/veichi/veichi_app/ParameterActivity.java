package com.example.veichi.veichi_app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class ParameterActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final int TIME_INTERVAL = 200;
    private static final int TIME_INTERVAL_FINAL = 20;
    private static final int MSG_INTERVAL_EMPTY = 10000;
    private static final int TASK_MONITOR_CONTROL = 10001;
    private static final int TASK_PARAMETER_CONTROL = 10002;




    private static final String TAG = "ParameterActivity";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private String wifiPassword = null;
    private Button wifiSearchButton;
    private WifiUtils localWifiUtils;
    private WifiManager mWifiManager;
    private List<ScanResult> wifiResultList;
    private List<WifiConfiguration> mWifiConfiguration;
//    private List<String> wifiListString = new ArrayList<String>();
    private ListView wifiSearchListView;
    private ArrayAdapter<String> arrayWifiAdapter;

//    private Handler mHandler;



    private FragmentManager fManager;


    private FragmentTransaction fTransaction;

    private StartButtonFragment fgStartButton;
    private WiFiListviewFragment fgWiFiListView;
    private MonitorFragment fgMonitor;

    Handler handler = null;
//    Message msg = handler.obtainMessage();
//    Message msg = Message.obtain(handler, TASK_MONITOR_CONTROL);

    private BroadcastReceiver bcReceiver;
    private static int functionGroup = 0;
    private static int functionGroupNum = 0;
    private static int groupCIndex = 0;
    private static int spinner3_number = 0;
    private static int spinner4_number = 0;

    private final static long TWICE_CLICK_INTERVAL=1000;// 两次按返回键的最大间隔

    private long firstClickTime=0; // 记录第一次按键的时间，如没有按键，则为0


//    private MonitorStatesFragment fgMonitorStates;
    private ParameterListviewFragment fgParameterListView;

    public static Context s_context;


    private ArrayList<String> parameterListString =   new ArrayList<String>();

    private ListView parameterListView;
    private ArrayAdapter<String> arrayParameterAdapter;
    private int InitFragmentViewCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        InitParameterlistview();
        InitFragmentView();
        InitWiFilistview();
        InitNetWork();
        InitHandler();
//        setContentView(R.layout.click_time_stamp);

        firstClickTime=TWICE_CLICK_INTERVAL*(-1);  //确保不会在万一窗口快速打开时误按返回键一次退出

    }


    public void InitNetWork(){

        NetManager.instance().init(this);
        s_context = this;

        SocketThreadManager.sharedInstance();

        regBroadcast();

    }




    private void InitParameterlistview() {
        parameterListView = (ListView) findViewById(R.id.parameterlistview);
        arrayParameterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parameterListString);
        parameterListView.setAdapter(arrayParameterAdapter);
        ListOnItemClickListener parameterListListener = new ListOnItemClickListener();
        parameterListView.setOnItemClickListener(parameterListListener);
    }

    private void InitFragmentView() {
        Log.i(TAG, "InitFragmentView:" + (InitFragmentViewCount++));
        fManager = getFragmentManager();
        fTransaction = fManager.beginTransaction();
        fgParameterListView = new ParameterListviewFragment();

        fgWiFiListView = new WiFiListviewFragment();
        fgStartButton = new StartButtonFragment();
        fgMonitor = new MonitorFragment();
        fTransaction.add(R.id.fragment_content,fgStartButton, "FRG_START_BUTTON");
        fTransaction.add(R.id.fragment_content,fgWiFiListView);
        fTransaction.add(R.id.fragment_content,fgMonitor);

        fTransaction.hide(fgWiFiListView);
        fTransaction.hide(fgMonitor);
        fTransaction.commit();

//        android.app.Fragment frag = fManager.findFragmentByTag("FRG_START_BUTTON");
//        if(frag==null){
//            fTransaction.add(R.id.fragment_content,fgStartButton, "FRG_START_BUTTON");
//        }
    }


    private void InitWiFilistview() {

//       wifiSearchListView = (ListView) findViewById(R.id.wifiListView);
//
//        arrayWifiAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wifiListString);

        localWifiUtils = new WifiUtils(ParameterActivity.this);

    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fgStartButton != null)fragmentTransaction.hide(fgStartButton);
        if(fgWiFiListView != null)fragmentTransaction.hide(fgWiFiListView);
        if(fgMonitor != null)fragmentTransaction.hide(fgMonitor);
        if(fgParameterListView != null)fragmentTransaction.hide(fgParameterListView);
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }

        // super.onBackPressed();

        if ( (System.currentTimeMillis()-firstClickTime)>TWICE_CLICK_INTERVAL){
            // 第一次按返回键，设置时间戳并显示提示信息
            firstClickTime=System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        } else {
            // 两次按返回键时间差小于预设值，则正常退出
            finish();
            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parameter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // TODO Auto-generated methodstub


        parameterListString.clear();
        fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);

        handler.removeCallbacksAndMessages(null);


        if (id == R.id.nav_camera) {
            if(fgStartButton == null){
                fTransaction.add(R.id.fragment_content,fgStartButton);
            }else{
                fTransaction.show(fgStartButton);
            }
        } else if (id == R.id.nav_gallery) {
            if(fgWiFiListView == null){
                fTransaction.add(R.id.fragment_content,fgWiFiListView);
            }else{
                fTransaction.show(fgWiFiListView);
            }
//            wifiListString.clear();
            Log.i("huangf","TASK_fgWiFiListView++++START........1111111..............");
            if (localWifiUtils.WifiCheckState() == WifiManager.WIFI_STATE_ENABLED) {//等待Wifi开启
                wifiResultList = localWifiUtils.getScanResults();
                localWifiUtils.getConfiguration();
                if (wifiResultList != null) fgWiFiListView.setWifiListData(wifiResultList, localWifiUtils);
                Log.i("huangf","TASK_fgWiFiListView++++START..........5555555............");
            }
        } else if (id == R.id.nav_slideshow) {
            Message msg = Message.obtain(handler, TASK_MONITOR_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_MONITOR_CONTROL;
            handler.sendMessage(msg);

            Log.i("huangf","TASK_MONITOR_CONTROL++++START......................");
            if(fgMonitor == null){
                fTransaction.add(R.id.fragment_content,fgMonitor);
            }else{
                fTransaction.show(fgMonitor);
            }
        } else if (id == R.id.nav_manage_f0) {
            functionGroup = 0;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.f0_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{

                fTransaction.show(fgParameterListView);
            }

        } else if (id == R.id.nav_manage_f1) {
            functionGroup = 1;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.f1_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        } else if (id == R.id.nav_manage_f2) {
            functionGroup = 2;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.f2_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        } else if (id == R.id.nav_manage_f3) {
            functionGroup = 3;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.f3_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        } else if (id == R.id.nav_manage_f4) {
            functionGroup = 4;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.f4_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        } else if (id == R.id.nav_manage_f5) {
            functionGroup = 5;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.f5_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        } else if (id == R.id.nav_manage_f6) {
            functionGroup = 6;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.f6_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        } else if (id == R.id.nav_manage_f7) {
            functionGroup = 7;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.f7_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        }else if (id == R.id.nav_manage_f8) {
            functionGroup = 8;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.f8_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        }else if (id == R.id.nav_manage_fa) {
            functionGroup = 10;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.fa_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        }else if (id == R.id.nav_manage_fb) {
            functionGroup = 11;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.fb_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        }else if (id == R.id.nav_manage_fc) {
            functionGroup = 12;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.fc_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        }else if (id == R.id.nav_manage_fd) {
            functionGroup = 13;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.fd_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }
        }else if (id == R.id.nav_manage_fe){
            functionGroup = 14;

            Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//            handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
//            msg.what = TASK_PARAMETER_CONTROL;
            handler.sendMessage(msg);

            parameter_index = Const.START_PARAMETER;

            Tools.scanResultToString(ParameterValue.fe_List, parameterListString,arrayParameterAdapter);
            if(fgParameterListView == null){
                fgParameterListView = new ParameterListviewFragment();
                fTransaction.add(R.id.fragment_content,fgParameterListView);
            }else{
                fTransaction.show(fgParameterListView);
            }

        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        // 不要忘记提交
        fTransaction.commit();
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Parameter Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private String strParameterCheck = null;
    private String strParameterValue = null;
    private String parameterValueInput = null;

    class ListOnItemClickListener implements AdapterView.OnItemClickListener {
//        String wifiItemSSID = null;
        private View selectedItem;
//        Intent intent = new Intent();

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            // TODO Auto-generated method stub
            Log.i("huangf", "ListOnItemClickListener+start...........");

            selectedItem = arg1;
            arg1.setBackgroundResource(R.color.common_google_signin_btn_text_dark_default);//点击的Item项背景设置
            strParameterCheck = arrayParameterAdapter.getItem(arg2);//获得选中的设备
            functionGroupNum = (int)arrayParameterAdapter.getItemId(arg2);

           // String[] ItemValue = wifiItem.split("--");
            //wifiItemSSID = ItemValue[0];
//            monitor_index = Const.SHOW_DIALOG_READY;

//            parameter_index = Const.START_PARAMETER;

//            if(!bDlg_READ_PARAMETER_EN)bDlg_READ_PARAMETER_EN = true;
            parameter_index = Const.READ_PARAMETER;

        }
    }

    public void showParameterDialog(){
        ParameterDialog paraDialog = new ParameterDialog(
                strParameterValue,
                strParameterCheck,
                ParameterActivity.this,
                new ParameterDialog.OnCustomDialogListener() {
                    @Override
                    public void back(String str) {
                        // TODO Auto-generated method stub
                        if(!TextUtils.isEmpty(str)){
                            parameterValueInput = Tools.strWriteParameter(str);
                            Log.i("huangf","parameterValueInput=" + "确定键value" + parameterValueInput);
                            parameter_index = Const.WRITE_PARAMETER;
                        }else{
                            parameterValueInput = "0";
                            //selectedItem.setBackgroundResource(R.color.burlywood);
                            Log.i("huangf","parameterValueInput=" + "取消键value"  + parameterValueInput);
                        }
                    }
                });
        paraDialog.show();
    }


    public void ReplaceStartButton2WifiList(){
//        findViewById(R.id.nav_slideshow).setEnabled(true);

//        if (Build.VERSION.SDK_INT >= 23) {
//            Settings.Secure.putInt(getContentResolver(),Settings.Secure.LOCATION_MODE, 1);
//        }
        localWifiUtils.WifiOpen();
        localWifiUtils.WifiStartScan();

        while (localWifiUtils.WifiCheckState() != WifiManager.WIFI_STATE_ENABLED) {//等待Wifi开启
            Log.i("WifiState", String.valueOf(localWifiUtils.WifiCheckState()));
        }
        try {
            Thread.sleep(3000);//休眠3s，不休眠则会在程序首次开启WIFI时候，处理getScanResults结果，wifiResultList.size()发生异常



        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


//        wifiListString.clear();
        wifiResultList = localWifiUtils.getScanResults();
        localWifiUtils.getConfiguration();
//        while(wifiResultList.size()==0){
//
//            wifiResultList = localWifiUtils.getScanResults();
//            Log.i("huangf","localWifiUtils.wifiResultList.size() ======== while");
//        }

        fgWiFiListView.setWifiListData(wifiResultList,localWifiUtils);



        fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        if(fgWiFiListView == null){
            fTransaction.add(R.id.fragment_content,fgWiFiListView);
        }else{
            fTransaction.show(fgWiFiListView);
        }

//        fTransaction.addToBackStack(null);
        fTransaction.commit();

    }



    public void ReplaceWifiList2Mointor(){

        fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        if(fgMonitor == null){
            fTransaction.add(R.id.fragment_content,fgMonitor);
        }else{
            fTransaction.show(fgMonitor);
        }
        fTransaction.commit();

    }


    private void InitHandler() {

        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {

                    case 9999:
                        //showMsg("发送socket失败");
                        break;

                    case 9998:

                        //showMsg("发送socket成功");
                        break;

                    case TASK_MONITOR_CONTROL:
                        setTaskMonitorControl();//访问数据主循环，被动TIME_INTERVAL延时后启动
                        break;

                    case TASK_PARAMETER_CONTROL:
                        setTaskParameterControl();
                        break;

                }
            }
        };

    }



    boolean bBut_FWD_RUNNING_EN = false;
    public void callFwdRunning(){
        monitor_index = Const.MONITOR_BUTTON_FWDRUNNING;
    }
    boolean bBut_SLOW_STOP_EN = false;
    public void callSlowStop(){
        monitor_index = Const.MONITOR_BUTTON_SLOWSTOP;
    }
    boolean bBut_REV_RUNNING_EN = false;
    public void callRevRunning(){
        monitor_index = Const.MONITOR_BUTTON_REVRUNNING;
    }
    boolean bBut_FREE_STOP_EN = false;
    public void callFreeStop(){
        monitor_index = Const.MONITOR_BUTTON_FREESTOP;
    }
    boolean bBut_ALARM_RESET_EN = false;
    public void callAlmReset(){
        monitor_index = Const.MONITOR_BUTTON_ALARMRESET;
//        Message msg = Message.obtain(handler, TASK_MONITOR_CONTROL);
//        handler.sendMessageDelayed(msg, TIME_INTERVAL);
    }
    boolean bREAD_PARAMETER_EN = false;

    //    boolean bBut_REFRESH_EN = false;
    public void callRsfresh(){
//        if(!bBut_REFRESH_EN)bBut_REFRESH_EN = true;
        monitor_index = Const.ANDWERED_MONITOR_RESTART;
        handler.removeMessages(TASK_MONITOR_CONTROL);
        Message msg = Message.obtain(handler, TASK_MONITOR_CONTROL);
        handler.sendMessageDelayed(msg, TIME_INTERVAL);
    }
//        public void callReadPara(){
//            if(!bDlg_READ_PARAMETER_EN)bDlg_READ_PARAMETER_EN = true;
//            parameter_index = Const.READ_PARAMETER;
//        }


    private int parameter_index = Const.START_PARAMETER;
    private void setTaskParameterControl(){
        switch (parameter_index) {
            case Const.START_PARAMETER:

                break;
            case Const.READ_PARAMETER:
                if(!bREAD_PARAMETER_EN){
                    bREAD_PARAMETER_EN = true;
                    SocketThreadManager
                            .sharedInstance()
                            .sendMsg(Tools.hexStringToByteArray(Tools.strReadParameter(functionGroup,functionGroupNum)), handler);
                }
                break;
            case Const.WRITE_PARAMETER:
                SocketThreadManager
                        .sharedInstance()
                        .sendMsg(Tools.hexStringToByteArray(Tools.strWriteParameter(functionGroup,functionGroupNum,parameterValueInput)), handler);
                break;
        }
//        handler.removeMessages(TASK_PARAMETER_CONTROL);
        Message msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//        msg = Message.obtain(handler, TASK_PARAMETER_CONTROL);
//        handler.sendMessage(msg);
        handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);
    }


    /**
     * TODO 循环调用的指令
     * 这里最好是在收到启动的回包后再开始循环，后续再优化
     */
    private int monitor_index = Const.ANDWERED_MONITOR_RESTART;
    boolean bSend_MONITOR_1 = false;
    boolean bSend_MONITOR_2 = false;
    boolean bSend_MONITOR_3 = false;
    boolean bSend_MONITOR_4 = false;
    boolean bSend_MONITOR_5 = false;
//    boolean bSend_Button_CMD = false;

    private void clearMonitor1to5(){
        bSend_MONITOR_1 = false;
        bSend_MONITOR_2 = false;
        bSend_MONITOR_3 = false;
        bSend_MONITOR_4 = false;
        bSend_MONITOR_5 = false;
    }

    public void setTaskMonitorControl() {
        // 启动起始序号的定时发送任务，用于监控参数的循环
        Log.d("huangf", "setTaskMonitorControl: monitor_index=" + String.valueOf(monitor_index));
        switch (monitor_index) {
            case Const.ANDWERED_MONITOR_RESTART:
                monitor_index = Const.ANDWERED_MONITOR_1;
                clearMonitor1to5();
                break;
            case Const.ANDWERED_MONITOR_1:
                if (!bSend_MONITOR_1){
                    bSend_MONITOR_1 = true;
                    SocketThreadManager
                            .sharedInstance()
                            .sendMsg(Tools.hexStringToByteArray(Tools.sendMonitorStr(fgMonitor.monitor_1_index)), handler);
                }
                break;
            case Const.ANDWERED_MONITOR_2:
                if (!bSend_MONITOR_2){
                    bSend_MONITOR_2 = true;
                    SocketThreadManager
                            .sharedInstance()
                            .sendMsg(Tools.hexStringToByteArray(Tools.sendMonitorStr(fgMonitor.monitor_2_index)), handler);
                }
                break;
            case Const.ANDWERED_MONITOR_3:
                if (!bSend_MONITOR_3){
                    bSend_MONITOR_3 = true;
                    SocketThreadManager
                            .sharedInstance()
                            .sendMsg(Tools.hexStringToByteArray(Tools.sendMonitorStr(fgMonitor.monitor_3_index)), handler);
                }
                break;
            case Const.ANDWERED_MONITOR_4:
                if (!bSend_MONITOR_4){
                    bSend_MONITOR_4 = true;
                    SocketThreadManager
                            .sharedInstance()
                            .sendMsg(Tools.hexStringToByteArray(Tools.sendMonitorStr(fgMonitor.monitor_4_index)), handler);
                }
                break;
            case Const.ANDWERED_MONITOR_5:
                if (!bSend_MONITOR_5){
                    bSend_MONITOR_5 = true;
                    SocketThreadManager
                            .sharedInstance()
                            .sendMsg(Tools.hexStringToByteArray(Tools.sendMonitorStr(fgMonitor.monitor_5_index)), handler);
                }
                break;
            case Const.MONITOR_BUTTON_FWDRUNNING:
                if(!bBut_FWD_RUNNING_EN){
                    bBut_FWD_RUNNING_EN = true;
                    SocketThreadManager
                            .sharedInstance()
                            .sendMsg(Tools.hexStringToByteArray(Tools.sendCmdStr(Const.FDW_RUNNING_CMD)), handler);
                }
                break;
            case Const.MONITOR_BUTTON_REVRUNNING:
                if(!bBut_REV_RUNNING_EN){
                    bBut_REV_RUNNING_EN = true;
                    SocketThreadManager
                            .sharedInstance()
                            .sendMsg(Tools.hexStringToByteArray(Tools.sendCmdStr(Const.REV_RUNNING_CMD)), handler);
                }
                break;
            case Const.MONITOR_BUTTON_FREESTOP:
                if(!bBut_FREE_STOP_EN){
                    bBut_FREE_STOP_EN = true;
                    SocketThreadManager
                            .sharedInstance()
                            .sendMsg(Tools.hexStringToByteArray(Tools.sendCmdStr(Const.FREE_STOP_CMD)), handler);
                }
                break;
            case Const.MONITOR_BUTTON_SLOWSTOP:
                if(!bBut_SLOW_STOP_EN){
                    bBut_SLOW_STOP_EN = true;
                    SocketThreadManager
                            .sharedInstance()
                            .sendMsg(Tools.hexStringToByteArray(Tools.sendCmdStr(Const.SLOW_STOP_CMD)), handler);
                }
                break;
            case Const.MONITOR_BUTTON_ALARMRESET:
                if(!bBut_ALARM_RESET_EN) {
                    bBut_ALARM_RESET_EN = true;
                    SocketThreadManager
                            .sharedInstance()
                            .sendMsg(Tools.hexStringToByteArray(Tools.sendCmdStr(Const.ALRM_RESET_CMD)), handler);
                }
                break;

            default:
                break;
        }

//        handler.removeMessages(TASK_MONITOR_CONTROL);
        Message msg = Message.obtain(handler, TASK_MONITOR_CONTROL);
//        msg = Message.obtain(handler, TASK_MONITOR_CONTROL);
        handler.sendMessageDelayed(msg, TIME_INTERVAL_FINAL);


//        Log.i("huangf", "callInterval ROLL="+String.valueOf(spinner1_number)+"...start...............");
    }


    public void regBroadcast() {

        bcReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String crc16Result = "0";
                String crc16Result_recv = "1";
//                final String value = intent.getStringExtra("response");
                String strRecvData = intent.getStringExtra("responsedata");


                if(strRecvData.length()>4){
                    //TODO 需要将得到str结果进行判断，若小于4则会异常
                    crc16Result_recv =  strRecvData.substring(strRecvData.length()-4, strRecvData.length());
                    String crc16Result_comp =  strRecvData.substring(0, strRecvData.length()-4);
                    String crc16Result_str = Tools.crc16(crc16Result_comp);
                    crc16Result = crc16Result_str.substring(crc16Result_str.length()-4, crc16Result_str.length());

                    if (crc16Result.equals(crc16Result_recv)){
                        String strFinalData =String.valueOf(Long.valueOf((strRecvData.substring(6, 10)), 16));
                        Log.i("huangf","strRecvData+FinalData="+strFinalData);



                        switch (monitor_index){
                            case Const.ANDWERED_MONITOR_RESTART:
                                break;
                            case Const.ANDWERED_MONITOR_1:
                                if(bSend_MONITOR_1) {
                                    bSend_MONITOR_1 = false;
                                    groupCIndex = 1600+fgMonitor.monitor_1_index;

                                    fgMonitor.view1.setText(Tools.strParaGroupReadDeal(strFinalData,groupCIndex));
                                    monitor_index = Const.ANDWERED_MONITOR_2;
                                }
                                break;
                            case Const.ANDWERED_MONITOR_2:
                                if(bSend_MONITOR_2) {
                                    bSend_MONITOR_2 = false;
                                    groupCIndex = 1600+fgMonitor.monitor_2_index;
                                    fgMonitor.view2.setText(Tools.strParaGroupReadDeal(strFinalData,groupCIndex));
                                    monitor_index = Const.ANDWERED_MONITOR_3;
                                }
                                break;
                            case Const.ANDWERED_MONITOR_3:
                                if(bSend_MONITOR_3) {
                                    bSend_MONITOR_3 = false;
                                    groupCIndex = 1600+fgMonitor.monitor_3_index;
                                    fgMonitor.view3.setText(Tools.strParaGroupReadDeal(strFinalData,groupCIndex));
                                    monitor_index = Const.ANDWERED_MONITOR_4;
                                }

                                break;
                            case Const.ANDWERED_MONITOR_4:
                                if(bSend_MONITOR_4){
                                    bSend_MONITOR_4 = false;
                                    groupCIndex = 1600+fgMonitor.monitor_4_index;
                                    fgMonitor.view4.setText(Tools.strParaGroupReadDeal(strFinalData,groupCIndex));
                                    monitor_index = Const.ANDWERED_MONITOR_5;
                                }
                                break;
                            case Const.ANDWERED_MONITOR_5:
                                if(bSend_MONITOR_5){
                                    bSend_MONITOR_5 = false;
                                    groupCIndex = 1600+fgMonitor.monitor_5_index;
                                    fgMonitor.view5.setText(Tools.strParaGroupReadDeal(strFinalData,groupCIndex));
                                    monitor_index = Const.ANDWERED_MONITOR_RESTART;
                                }
                                break;

                            case Const.MONITOR_BUTTON_REVRUNNING:
                                if(bBut_REV_RUNNING_EN){
                                    bBut_REV_RUNNING_EN = false;
                                    callRsfresh();
                                }
                                break;
                            case Const.MONITOR_BUTTON_FWDRUNNING:
                                if(bBut_FWD_RUNNING_EN){
                                    bBut_FWD_RUNNING_EN = false;
                                    callRsfresh();
                                }
                                break;
                            case Const.MONITOR_BUTTON_FREESTOP:
                                if(bBut_FREE_STOP_EN){
                                    bBut_FREE_STOP_EN = false;
                                    callRsfresh();
                                }
                                break;
                            case Const.MONITOR_BUTTON_SLOWSTOP:
                                if(bBut_SLOW_STOP_EN){
                                    bBut_SLOW_STOP_EN = false;
                                    callRsfresh();
                                }
                                break;
                            case Const.MONITOR_BUTTON_ALARMRESET:
                                if(bBut_ALARM_RESET_EN){
                                    bBut_ALARM_RESET_EN = false;
                                    callRsfresh();
                                }
                                break;
                            default:
                                break;
                        }
                        switch (parameter_index) {
                            case Const.START_PARAMETER:
                                bREAD_PARAMETER_EN = false;
                                break;
                            case Const.READ_PARAMETER:
                                if(bREAD_PARAMETER_EN) {
                                    bREAD_PARAMETER_EN = false;
                                    strParameterValue = Tools.strParaGroupReadDeal(strFinalData, (functionGroup * 100 + functionGroupNum));
                                    showParameterDialog();
                                    parameter_index = Const.START_PARAMETER;
                                }
                                break;
                            case Const.WRITE_PARAMETER:
                                parameter_index = Const.START_PARAMETER;
                                // TODO 解决如何显示修改成功
                                break;
                        }


                    }else {
                        Log.i("huangf","BroadcastReceiver+responsedata+strRecvData.length()>5+crc16Fault");
                        monitor_index = Const.ANDWERED_MONITOR_RESTART;
                        parameter_index = Const.START_PARAMETER;
                    }
                }
                strRecvData = null;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // textView.setText(value);
                       // textViewContent.setText(crc16Result);
                    }
                });
            }
        };
        IntentFilter intentToReceiveFilter = new IntentFilter();
        intentToReceiveFilter.addAction(Const.BC);
        registerReceiver(bcReceiver, intentToReceiveFilter);
    }


}
