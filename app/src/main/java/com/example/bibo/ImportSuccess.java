package com.example.bibo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ImportSuccess extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_import_success);
//    }

    OnGoBackMenu goBackMenu;
    public interface OnGoBackMenu{
        public void GoBackMenuPerform();
    }

    //DataType of splashScreen time
    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_import_success, container ,false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent splashIntent = new Intent(getContext(),Menu.class);
//                startActivity(splashIntent);
                goBackMenu.GoBackMenuPerform();
            }
        },SPLASH_SCREEN_TIME_OUT);
        return view;
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        goBackMenu = (OnGoBackMenu) activity;
    }
}
