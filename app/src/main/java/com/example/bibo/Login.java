package com.example.bibo;

//import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bibo.api.ApiClient;
import com.example.bibo.api.ApiInterface;
import com.example.bibo.api.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends Fragment {
    private TextView RegText;

    private EditText Username , UserPassword;
    private Button LoginBtn;

    OnLoginFormActivityListener loginFormActivityListener;

    public interface OnLoginFormActivityListener{
        public void performRegister();
        public void performLogin(String username);
    }

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false);
        View view = inflater.inflate(R.layout.activity_login, container, false);

        RegText = view.findViewById(R.id.SignUp_txt);

        Username = view.findViewById(R.id.Username);
        UserPassword = view.findViewById(R.id.Password);
        LoginBtn = view.findViewById(R.id.LogInButton);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFormActivityListener.performRegister();
            }
        });
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (OnLoginFormActivityListener) activity;
    }

    private void performLogin(){
        String username = Username.getText().toString();
        String password = UserPassword.getText().toString();

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
//
        Call<User> call = service.performUserLogin(username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("Response",response.message());
                if (response.body().getResponse().equals("ok")){
                    MainActivity.preConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getUsername());
                }else if(response.body().getResponse().equals("failed")){
                    MainActivity.preConfig.displayToast("Login Failed.PLease Try again!!!");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Show error",t.getLocalizedMessage());
            }
        });
        Username.setText("");
        UserPassword.setText("");
    }
}
