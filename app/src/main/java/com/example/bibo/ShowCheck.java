package com.example.bibo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ShowCheck extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_check);
//    }
    private TextView bID,title,genre,pYear,qty,price;
    private Button okBtn;

    OnGoToMenu goToMenu;
    public interface OnGoToMenu{
        public void PerformShowCheck();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_show_check,container,false);
        bID = view.findViewById(R.id.bID);
        title = view.findViewById(R.id.title);
        genre = view.findViewById(R.id.genre);
        pYear = view.findViewById(R.id.pYear);
        qty = view.findViewById(R.id.qty);
        price = view.findViewById(R.id.price);

        bID.setText(MainActivity.preConfig.readBID());
        title.setText(MainActivity.preConfig.readTitle());
        genre.setText(MainActivity.preConfig.readGenre());
        pYear.setText(MainActivity.preConfig.readPyear());
        qty.setText(MainActivity.preConfig.readQty());
        price.setText(MainActivity.preConfig.readPrice());

        okBtn = view.findViewById(R.id.okBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenu.PerformShowCheck();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        goToMenu =  (OnGoToMenu) activity;
    }
}
