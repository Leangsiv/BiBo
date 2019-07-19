package com.example.bibo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
//    }

    private Button logoutBtn;
    OnLogoutListener logoutListener;

    private ImageButton exportBtn;
    private ImageButton importBtn;
    private ImageButton checkBtn;

    public interface OnLogoutListener{
        public void LogoutPerform();
        public void ExportPerform();
        public void ImportPerform();
        public void CheckPerform();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_menu, container ,false);

        //Logout
        logoutBtn = view.findViewById(R.id.LogOutButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutListener.LogoutPerform();
            }
        });

        //Exporting
        exportBtn = view.findViewById(R.id.exportImageBtn);
        exportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutListener.ExportPerform();
            }
        });

        //Importing
        importBtn = view.findViewById(R.id.importImageBtn);
        importBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutListener.ImportPerform();
            }
        });

        //Checking
        checkBtn = view.findViewById(R.id.checkImageBtn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutListener.CheckPerform();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener = (OnLogoutListener) activity;
    }
}
