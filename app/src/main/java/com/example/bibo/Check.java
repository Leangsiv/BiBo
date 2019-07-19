package com.example.bibo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bibo.api.ApiClient;
import com.example.bibo.api.ApiInterface;
import com.example.bibo.api.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Check extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_check);
//    }

    //Declare
    private EditText BookID;
    private Button checkBtn;

    //Listener
    OnCheckListener checkListener;

    //Interface
    public interface OnCheckListener{
        public void performCheck(String bID,String title,String genre,String pYear,String qty,String price);
    }

    public Check(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_check,container,false);

        BookID = view.findViewById(R.id.bookID);

        checkBtn = view.findViewById(R.id.checkBtn);

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCheck();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        checkListener = (OnCheckListener) activity;
    }

    private void performCheck(){
        String bID = BookID.getText().toString();

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        Call<User> call = service.performChecking(bID);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")){
                    MainActivity.preConfig.writeLoginStatus(true);
                    checkListener.performCheck(response.body().getbID(),
                                                response.body().getTitle(),
                                                response.body().getGenre(),
                                                response.body().getpYear(),
                                                response.body().getQty(),
                                                response.body().getPrice());
                }else if(response.body().getResponse().equals("failed")){
                    MainActivity.preConfig.displayToast("Checking Failed!!!");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Show error Check", call.toString());
            }
        });
        BookID.setText("");
    }
}
