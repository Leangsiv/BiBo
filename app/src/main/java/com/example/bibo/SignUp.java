package com.example.bibo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bibo.api.ApiClient;
import com.example.bibo.api.ApiInterface;
import com.example.bibo.api.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//    }

    private EditText Fname ,Lname, Username , UserPassword, Email, PhoneNumber;
    private Button RegisterBtn;

    Login.OnLoginFormActivityListener loginFormActivityListener;

    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_registration, container, false);
        View view = inflater.inflate(R.layout.activity_sign_up, container, false);
        Fname = view.findViewById(R.id.Firstname);
        Lname = view.findViewById(R.id.Lastname);
        Username = view.findViewById(R.id.UsernameInSignUp);
        UserPassword = view.findViewById(R.id.PasswordInSignup);
        Email = view.findViewById(R.id.EmailInSignUp);
        PhoneNumber = view.findViewById(R.id.PhoneInSignUp);

        RegisterBtn = view.findViewById(R.id.SignUpButtonInSignUp);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });


        return view;
    }

    public void performRegistration(){
        String fname = Fname.getText().toString();
        String lname = Lname.getText().toString();
        String username = Username.getText().toString();
        String password = UserPassword.getText().toString();
        String email = Email.getText().toString();
        String phoneNum = PhoneNumber.getText().toString();

        //Call<User> call = MainActivity.apiInterface.performRegistration(name,username,password);
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<User> call = service.performRegistration(fname,lname,username,password,email,phoneNum);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //Intent intent = new Intent(getContext(),MainActivity.class);
                if (response.body().getResponse().equals("ok")){
                    MainActivity.preConfig.displayToast("Registration success!!!");
                    //startActivity(intent);
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0,0);

                }else if(response.body().getResponse().equals("exist")){
                    MainActivity.preConfig.displayToast("User already exist!!!");
                }else if(response.body().getResponse().equals("error")){
                    MainActivity.preConfig.displayToast("Something went wrong!!!");
                }else{
                    Toast.makeText(getContext(),"Nothing",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Show error regs", call.toString());
            }
        });
        Fname.setText("");
        Lname.setText("");
        UserPassword.setText("");
        Username.setText("");
        Email.setText("");
        PhoneNumber.setText("");
    }
}
