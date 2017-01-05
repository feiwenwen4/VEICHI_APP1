package com.example.veichi.veichi_app;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.ContentValues.TAG;


public class StartButtonFragment extends Fragment {
    private Button wifiSearchButton;
//    private WifiUtils localWifiUtils;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_button, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
//        localWifiUtils = new WifiUtils(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        wifiSearchButton = (Button) getActivity().findViewById(R.id.butStartScanWiFi);
        WIFIButtonListener wifiButtonListener = new WIFIButtonListener();
        wifiSearchButton.setOnClickListener(wifiButtonListener);
    }


    class WIFIButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) { // TODO Auto-generated method stub
//

//            localWifiUtils.WifiOpen();

//            ((ParameterActivity)getActivity()).setWifi(true);
//              setWifi(true);
//            wifiSearchButton.setText("开启WiFi.......");
            ((ParameterActivity)getActivity()).ReplaceStartButton2WifiList();


        }
    }





}
