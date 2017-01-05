package com.example.veichi.veichi_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class SD650InitFragment extends Fragment implements View.OnClickListener {

    private TextView textView;
    public TextView tvPressure,tvSpeed,tvCurrent,tvMotorStatus,tvF502,tvF504,tvF506;
    private Button btnSetting,btnJOG;
    private Spinner spinner500;
    private Spinner spinner501;
//    private Spinner spinner502;
    private Spinner spinner503;
//    private Spinner spinner504;
    private Spinner spinner505;
//    private Spinner spinner506;
    private Spinner spinner016;
    private Spinner spinnerSetting;

    private ArrayAdapter adapter501,adapter503,adapterSetting,adapter4,adapter5;
    public int parameter501Index,parameter503Index,settingIndex;

    private ParameterActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mointor_view, null);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnSetting = (Button) getActivity().findViewById(R.id.button5);
        btnJOG = (Button) getActivity().findViewById(R.id.button4);


        btnSetting.setOnClickListener(this);
        btnJOG.setOnClickListener(this);


        spinner500 = (Spinner) getActivity().findViewById(R.id.spinner_f500);
        spinner501 = (Spinner) getActivity().findViewById(R.id.spinner_f501);
//        spinner502 = (Spinner) getActivity().findViewById(R.id.spinner_f502);
        spinner503 = (Spinner) getActivity().findViewById(R.id.spinner_f503);
//        spinner504 = (Spinner) getActivity().findViewById(R.id.spinner_f504);
        spinner505 = (Spinner) getActivity().findViewById(R.id.spinner_f505);
//        spinner506 = (Spinner) getActivity().findViewById(R.id.spinner_f506);
        spinner016 = (Spinner) getActivity().findViewById(R.id.spinner_f016);
        spinnerSetting = (Spinner) getActivity().findViewById(R.id.spinner7);

        tvPressure = (TextView) getActivity().findViewById(R.id.textView_monitor_press);
        tvCurrent = (TextView) getActivity().findViewById(R.id.textView_monitor_current);
        tvSpeed = (TextView) getActivity().findViewById(R.id.textView_monitor_speed);
        tvMotorStatus = (TextView) getActivity().findViewById(R.id.textView_monitor_status);
        tvF502 = (TextView) getActivity().findViewById(R.id.textView_monitor_F502);
        tvF504 = (TextView) getActivity().findViewById(R.id.textView_monitor_F504);
        tvF506 = (TextView) getActivity().findViewById(R.id.textView_monitor_F506);


        adapter501 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_f501, android.R.layout.simple_spinner_item);
        adapter503 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_f503, android.R.layout.simple_spinner_item);
        adapterSetting = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array_Setting, android.R.layout.simple_spinner_item);

        //设置下拉列表的风格
        adapter501.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        adapter503.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        adapterSetting.setDropDownViewResource(android.R.layout.simple_list_item_checked);


        //将adapter2 添加到spinner中
        spinner501.setAdapter(adapter501);
        spinner503.setAdapter(adapter503);
        spinnerSetting.setAdapter(adapterSetting);


        //添加事件Spinner事件监听
        spinner501.setOnItemSelectedListener(new SD650InitFragment.SpinnerXMLSelectedListener_spinner501());
        spinner503.setOnItemSelectedListener(new SD650InitFragment.SpinnerXMLSelectedListener_spinner503());
        spinnerSetting.setOnItemSelectedListener(new SD650InitFragment.SpinnerXMLSelectedListener_spinnerSetting());


        //设置默认值
        spinner501.setVisibility(View.VISIBLE);
        spinner503.setVisibility(View.VISIBLE);
        spinnerSetting.setVisibility(View.VISIBLE);
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


    //使用XML形式操作 Spinner501
    class SpinnerXMLSelectedListener_spinner501 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            //view1.setText("第一显示参数XXXX"+adapter2.getItem(arg2));
            // view1.setText("第一显示参数"+adapter1.getItemId(arg2)); //int monitor1_num=spinner1.getId();
            parameter501Index = (int)adapter501.getItemId(arg2);
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }
    //使用XML形式操作 Spinner503
    class SpinnerXMLSelectedListener_spinner503 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            // view2.setText("第二显示参数"+adapter2.getItem(arg2));
            parameter503Index = (int)adapter503.getItemId(arg2);
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }
    //使用XML形式操作 SpinnerSetting
    class SpinnerXMLSelectedListener_spinnerSetting implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            // view2.setText("第二显示参数"+adapter2.getItem(arg2));
            settingIndex = (int)adapterSetting.getItemId(arg2);
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }


}