package com.example.bibo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AddMore extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_more);
//    }
        //Remove Action Bar
//        if(getSupportActionBar()!=null)
//            this.getSupportActionBar().hide();

    //Declare DataType
    private Button yesBtn , noBtn;

    //Create Listener
    OnChooseListener chooseListener;

    //Interface of Listener
    public interface OnChooseListener{
        public void AddingPerform();
        public void GoToExportSuccess();
    }

    //OnCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_more, container ,false);

        yesBtn = view.findViewById(R.id.yesBtn);
        noBtn = view.findViewById(R.id.noBtn);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseListener.AddingPerform();
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseListener.GoToExportSuccess();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        chooseListener = (OnChooseListener) activity;
    }
}
