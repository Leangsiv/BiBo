package com.example.bibo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
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

public class Adding extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_adding);
//        //Remove Action Bar
//        if(getSupportActionBar()!=null)
//            this.getSupportActionBar().hide();
//    }

    //Declare DataType
    private EditText bNameOrID, qty;
    private Button addingSubmit;

    //Create Listener
    OnGoAddMoreListener goAddMoreListener;

    //Interface of Listener
    public interface OnGoAddMoreListener{
        public void GoAddMore();
    }

    //Default Method
    public Adding(){}

    //Set onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_adding,container,false);
        bNameOrID = view.findViewById(R.id.bookIdAndName);
        qty = view.findViewById(R.id.qtyExport);

        addingSubmit = view.findViewById(R.id.exportAddBtn);

        addingSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAdding();

                MainActivity.preConfig.displayToast("Adding success!!!");
                //Redirect to AddMore
                goAddMoreListener.GoAddMore();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        goAddMoreListener = (OnGoAddMoreListener) activity;
    }

    public void performAdding(){
        String bookNameOrID = bNameOrID.getText().toString();
        int qtyExport = Integer.parseInt(qty.getText().toString());

        ///////
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<User> call = service.performAdding(bookNameOrID,qtyExport);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")){
                    //Toast.makeText(getContext(),"Adding Success",Toast.LENGTH_SHORT).show();
                }else {
                    //Toast.makeText(getContext(),"Adding Failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Show error of Adding",call.toString());
            }
        });

        bNameOrID.setText("");
        qty.setText("");
    }
}
