package com.example.veichi.veichi_app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ParameterDialog extends Dialog {
    private Button cancelButton;
    private Button okButton;
    private EditText pswEdit;
    public TextView value;
    private String readValue;
    private OnCustomDialogListener customDialogListener;
    private String titleValue;
    public ParameterDialog(String Value,String strTitle,Context context,OnCustomDialogListener customListener) {
        //OnCancelListener cancelListener) {
        super(context);
        // TODO Auto-generated constructor stub
        customDialogListener = customListener;
        titleValue = strTitle;
        readValue = Value;
    }

    //定义dialog的回调事件
    public interface OnCustomDialogListener{
        void back(String str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parameter_config_dialog);
        //setTitle("请输入密码");
        setTitle(titleValue);
        pswEdit = (EditText)findViewById(R.id.wifiDialogPsw);
        value = (TextView) findViewById(R.id.parameterReadValue);
        cancelButton = (Button)findViewById(R.id.wifiDialogCancel);
        okButton = (Button)findViewById(R.id.wifiDialogCertain);
        cancelButton.setOnClickListener(buttonDialogListener);
        okButton.setOnClickListener(buttonDialogListener);
        value.setText(readValue);


    }

    private View.OnClickListener buttonDialogListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            // TODO Auto-generated method stub
            if(view.getId() == R.id.wifiDialogCancel){
                pswEdit = null;
                customDialogListener.back(null);
                cancel();//自动调用dismiss();
            }
            else{
                customDialogListener.back(pswEdit.getText().toString());
                dismiss();
            }
        }
    };

}