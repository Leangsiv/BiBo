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
import com.example.bibo.api.PreConfig;
import com.example.bibo.api.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Export extends Fragment {

    //Declare DataType
    private EditText cName, pNumber, bNameOrID, qty;
    private Button exportSubmit;

    //Create Listener
    OnAddingListener addingListener;

    //Interface for Listener
    public interface OnAddingListener{
        public void AddMorePerform();

    }

    public Export(){

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_export);
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_export,container,false);
        cName = view.findViewById(R.id.custName);
        pNumber = view.findViewById(R.id.phoneNumber);
        bNameOrID = view.findViewById(R.id.bookIdAndName);
        qty = view.findViewById(R.id.qtyExport);

        exportSubmit = view.findViewById(R.id.exportBtn);

        exportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performExport();
                MainActivity.preConfig.displayToast("Export success!!!");

                //Redirect to Add More
                addingListener.AddMorePerform();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        addingListener = (OnAddingListener) activity;
    }

    public void performExport(){
        String customerName = cName.getText().toString();
        String ph = pNumber.getText().toString();
        String bookNameOrID = bNameOrID.getText().toString();
        int qtyExport = Integer.parseInt(qty.getText().toString());

        ///////
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<User> call = service.performExport(customerName,ph,bookNameOrID,qtyExport);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")){
                    Toast.makeText(getContext(),"Export Success",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"Export Failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Show error of Exporting",call.toString());
            }
        });
        cName.setText("");
        pNumber.setText("");
        bNameOrID.setText("");
        qty.setText("");
    }
}
