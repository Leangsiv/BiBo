package com.example.bibo;

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
import android.widget.Toast;

import com.example.bibo.api.ApiClient;
import com.example.bibo.api.ApiInterface;
import com.example.bibo.api.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Import extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_import);
//    }
private EditText publisherName, title, genre, publication_year,quantity,price;
    private Button ImportButton;

    //Create Listener
    OnImportSuccess importSuccess;

    //Interface
    public interface OnImportSuccess{
        public void ImportSuccessPerform();
    }


    public Import() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_import);
        View view = inflater.inflate(R.layout.activity_import, container,false);
        //View view1 = inflater.inflate(R.layout.activity_import_success,container,false);

        publisherName = view.findViewById(R.id.publisherName);
        title = view.findViewById(R.id.title);
        genre = view.findViewById(R.id.genre);
        publication_year = view.findViewById(R.id.pYear);
        quantity = view.findViewById(R.id.qtyImport);
        price = view.findViewById(R.id.price);

        ImportButton = view.findViewById(R.id.importBtn);

        ImportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logoutListener.ImportSuccessPerform();
                performImportation();
                MainActivity.preConfig.displayToast("Importation success!!!");
                //startActivity(new Intent(Import.this,ImportSuccess.class));
                importSuccess.ImportSuccessPerform();

            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        importSuccess = (OnImportSuccess) activity;
    }

    public void performImportation(){
        String Publisher_name = publisherName.getText().toString();
        String Title = title.getText().toString();
        String Genre = genre.getText().toString();
        String Publication_year = publication_year.getText().toString();
        int Quantity = Integer.parseInt(quantity.getText().toString());
        float Price = Float.parseFloat(price.getText().toString());

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<User> call = service.performImportation(Publisher_name,Title,Genre,Publication_year,Quantity,Price);
        call.enqueue(new Callback<User>(){
            @Override
            public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                //Intent intent = new Intent(getContext(),MainActivity.class);
                if (response.body().getResponse().equals("Ok")){

                }else if(response.body().getResponse().equals("error")){
                    MainActivity.preConfig.displayToast("Something went wrong!!!");
                }else{
                    //Toast.makeText(getContext(),"Nothing",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                Log.e("Show error regs", call.toString());
            }
        });

        publisherName.setText("");
        title.setText("");
        genre.setText("");
        publication_year.setText("");
        quantity.setText("");
        price.setText("");
    }
}
