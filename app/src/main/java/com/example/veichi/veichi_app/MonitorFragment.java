package com.example.veichi.veichi_app;

import android.app.Activity;
import android.app.Fragment;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import static android.os.Looper.getMainLooper;

public class MonitorFragment extends Fragment implements View.OnClickListener {

    private TextView textView;
    public TextView view1,view2,view3,view4,view5;
    private Button btnRefresh,btnSlowStop,btnFreeStop,btnRevStart,btnFwdStart,btnAlmReset;
    private Spinner spinner1,spinner2,spinner3,spinner4,spinner5;
    private ArrayAdapter adapter1,adapter2,adapter3,adapter4,adapter5;
    public int monitor_1_index,monitor_2_index,monitor_3_index,monitor_4_index,monitor_5_index;

    private ParameterActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mointor_view, null);
    }

//    public void setHandler2Monitor(Handler mainHandler){
//        mHandler = mainHandler;
//    }
//
//    private Handler mHandler = new Handler() {//getMainLooper()
//        @Override
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case Const.ANDWERED_MONITOR_1:
//                    Log.i("huangf","Monitor_Spinner1_ANDWERED_MONITOR_1"+(String) msg.obj);
//                    view1.setText((String) msg.obj);
//                    break;
//                case Const.ANDWERED_MONITOR_2:
//                    view2.setText((String) msg.obj);
//                    break;
//                case Const.ANDWERED_MONITOR_3:
//                    view3.setText((String) msg.obj);
//                    break;
//                case Const.ANDWERED_MONITOR_4:
//                    view4.setText((String) msg.obj);
//                    break;
//            }
//        };
//    };
//
//    public void setHandler2Monitor(Handler mainHandler) {
//        Log.i("huangf","setHandler2Monitor..................");
//        mHandler = mainHandler;
//    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        mActivity = (ParameterActivity) activity;
//        mActivity.setHandler2Monitor(mHandler);
//
//    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnRefresh = (Button) getActivity().findViewById(R.id.refreshbutton);
        btnSlowStop = (Button) getActivity().findViewById(R.id.stop_btn);
        btnFreeStop = (Button) getActivity().findViewById(R.id.button2);
        btnRevStart = (Button) getActivity().findViewById(R.id.start_btn);
        btnFwdStart = (Button) getActivity().findViewById(R.id.button);
        btnAlmReset = (Button) getActivity().findViewById(R.id.button3);

        btnRefresh.setOnClickListener(this);
        btnSlowStop.setOnClickListener(this);
        btnFreeStop.setOnClickListener(this);
        btnRevStart.setOnClickListener(this);
        btnFwdStart.setOnClickListener(this);
        btnAlmReset.setOnClickListener(this);

        spinner1 = (Spinner) getActivity().findViewById(R.id.spinner_monitor_1);
        spinner2 = (Spinner) getActivity().findViewById(R.id.spinner_monitor_2);
        spinner3 = (Spinner) getActivity().findViewById(R.id.spinner_monitor_3);
        spinner4 = (Spinner) getActivity().findViewById(R.id.spinner_monitor_4);
        spinner5 = (Spinner) getActivity().findViewById(R.id.spinner_monitor_5);
        view1 = (TextView) getActivity().findViewById(R.id.textView_monitor_1);
        view2 = (TextView) getActivity().findViewById(R.id.textView_monitor_2);
        view3 = (TextView) getActivity().findViewById(R.id.textView_monitor_3);
        view4 = (TextView) getActivity().findViewById(R.id.textView_monitor_4);
        view5 = (TextView) getActivity().findViewById(R.id.textView_monitor_5);

        if(Const.bGroup_C_Chose_AC100){
            //将可选内容与ArrayAdapter连接起来
            adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_monitorAC100, android.R.layout.simple_spinner_item);
            adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_monitorAC100, android.R.layout.simple_spinner_item);
            adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_monitorAC100, android.R.layout.simple_spinner_item);
            adapter4 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_monitorAC100, android.R.layout.simple_spinner_item);
            adapter5 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_monitorAC100, android.R.layout.simple_spinner_item);
        }

        if(Const.bGroup_C_Chose_SD650){
            adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_monitorSD650, android.R.layout.simple_spinner_item);
            adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_monitorSD650, android.R.layout.simple_spinner_item);
            adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_monitorSD650, android.R.layout.simple_spinner_item);
            adapter4 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_monitorSD650, android.R.layout.simple_spinner_item);
            adapter5 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_monitorSD650, android.R.layout.simple_spinner_item);
        }


        //设置下拉列表的风格
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        adapter3.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        adapter4.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        adapter5.setDropDownViewResource(android.R.layout.simple_list_item_checked);

        //将adapter2 添加到spinner中
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        spinner4.setAdapter(adapter4);
        spinner5.setAdapter(adapter5);

        //添加事件Spinner事件监听
        spinner1.setOnItemSelectedListener(new SpinnerXMLSelectedListener_spinner1());
        spinner2.setOnItemSelectedListener(new SpinnerXMLSelectedListener_spinner2());
        spinner3.setOnItemSelectedListener(new SpinnerXMLSelectedListener_spinner3());
        spinner4.setOnItemSelectedListener(new SpinnerXMLSelectedListener_spinner4());
        spinner5.setOnItemSelectedListener(new SpinnerXMLSelectedListener_spinner5());

        //设置默认值
        spinner1.setVisibility(View.VISIBLE);
        spinner2.setVisibility(View.VISIBLE);
        spinner3.setVisibility(View.VISIBLE);
        spinner4.setVisibility(View.VISIBLE);
        spinner5.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
//        ((ParameterActivity)getActivity()).sendMonitorIndex2Activity(monitor_1_index,monitor_2_index,monitor_3_index,monitor_4_index);
        switch (v.getId()) {
            case R.id.refreshbutton://刷新按钮
                ((ParameterActivity)getActivity()).callRsfresh();
                break;
            case R.id.start_btn://反转运行
                ((ParameterActivity)getActivity()).callRevRunning();
                break;
            case R.id.stop_btn://减速停机
                ((ParameterActivity)getActivity()).callSlowStop();
                break;
            case R.id.button://正转运行
                ((ParameterActivity)getActivity()).callFwdRunning();
                break;
            case R.id.button2://自由停机
                ((ParameterActivity)getActivity()).callFreeStop();
                break;
            case R.id.button3://故障复位
                ((ParameterActivity)getActivity()).callAlmReset();
                break;
        }
    }


    //使用XML形式操作 Spinner
    class SpinnerXMLSelectedListener_spinner1 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            //view1.setText("第一显示参数XXXX"+adapter2.getItem(arg2));
            // view1.setText("第一显示参数"+adapter1.getItemId(arg2)); //int monitor1_num=spinner1.getId();
            monitor_1_index = (int)adapter1.getItemId(arg2);
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }
    //使用XML形式操作 Spinner2
    class SpinnerXMLSelectedListener_spinner2 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            // view2.setText("第二显示参数"+adapter2.getItem(arg2));
            monitor_2_index = (int)adapter2.getItemId(arg2);
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }
    //使用XML形式操作 Spinner3
    class SpinnerXMLSelectedListener_spinner3 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            //view3.setText("第三显示参数"+adapter3.getItem(arg2));
            monitor_3_index = (int)adapter3.getItemId(arg2);
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }
    //使用XML形式操作 Spinner4
    class SpinnerXMLSelectedListener_spinner4 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            // view4.setText("第四显示参数"+adapter4.getItem(arg2));
            monitor_4_index = (int)adapter4.getItemId(arg2);
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }

    //使用XML形式操作 Spinner5
    class SpinnerXMLSelectedListener_spinner5 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            // view5.setText("第四显示参数"+adapter4.getItem(arg2));
            monitor_5_index = (int)adapter5.getItemId(arg2);
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }





}
