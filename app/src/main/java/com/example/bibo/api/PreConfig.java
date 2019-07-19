package com.example.bibo.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.bibo.R;

public class PreConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public PreConfig(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }

    public boolean readLoginStatus(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void writeName(String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_username),name);
        editor.commit();
    }

    public String readName(){
        return sharedPreferences.getString(context.getString(R.string.pref_username),"User");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void writeBID(String bID){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_bid),bID);
        editor.commit();
    }

    public String readBID(){
        return sharedPreferences.getString(context.getString(R.string.pref_bid),"ID");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void writeTitle(String title){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_title),title);
        editor.commit();
    }

    public String readTitle(){
        return sharedPreferences.getString(context.getString(R.string.pref_title),"title");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void writeGenre(String genre){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_genre),genre);
        editor.commit();
    }

    public String readGenre(){
        return sharedPreferences.getString(context.getString(R.string.pref_genre),"genre");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void writePyear(String pYear){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_pyear),pYear);
        editor.commit();
    }

    public String readPyear(){
        return sharedPreferences.getString(context.getString(R.string.pref_pyear),"pYear");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void writeQty(String qty){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_qty),qty);
        editor.commit();
    }

    public String readQty(){
        return sharedPreferences.getString(context.getString(R.string.pref_qty),"qty");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void writePrice(String price){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_price),price);
        editor.commit();
    }

    public String readPrice(){
        return sharedPreferences.getString(context.getString(R.string.pref_price),"price");
    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void displayToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
