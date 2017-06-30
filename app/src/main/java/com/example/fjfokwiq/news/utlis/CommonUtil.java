package com.example.fjfokwiq.news.utlis;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.example.fjfokwiq.news.MyApplication;

public class CommonUtil {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    public static final int REQUEST_CODE_SELECT = 0X86;
    public static final int REQUEST_CODE_LOGIN = 0X56;

    public static void initPreferences(){
        preferences = MyApplication.context.getSharedPreferences("statusInfo", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static SharedPreferences getPreferences(){
        return preferences;
    }
    public static SharedPreferences.Editor getEditor(){
        return editor;
    }

    /*网络检测*/
    public static boolean isNetworkAvailable() {
            ConnectivityManager cm = (ConnectivityManager) MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if(info !=null){
                return info.isConnected();
            }

        return false;
    }

    public static void showToast(Context context,String text){
        Toast toast=null;
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();

    }


    public static void showSnackBar(View view, String text) {
        Snackbar bar=null;
        if (bar == null) {
            bar=Snackbar.make(view,text,Snackbar.LENGTH_SHORT)
                    .setAction("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
        }else {
            bar.setText(text);
        }

        bar.show();
    }
}
