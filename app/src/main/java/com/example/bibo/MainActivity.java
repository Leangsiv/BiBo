package com.example.bibo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bibo.api.ApiInterface;
import com.example.bibo.api.PreConfig;

public class MainActivity extends AppCompatActivity implements Login.OnLoginFormActivityListener , Menu.OnLogoutListener , Export.OnAddingListener , AddMore.OnChooseListener,Adding.OnGoAddMoreListener ,Import.OnImportSuccess, ImportSuccess.OnGoBackMenu,Check.OnCheckListener,ShowCheck.OnGoToMenu,ExportSuccess.OnReturnToMenu{

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
public static PreConfig preConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Remove Action Bar
        if(getSupportActionBar()!=null)
            this.getSupportActionBar().hide();

        preConfig = new PreConfig(this);
//
//        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
            if (preConfig.readLoginStatus()){
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new Menu()).commit();
            }else{
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new Login()).commit();
            }
        }
    }

    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new SignUp()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String username) {
        preConfig.writeName(username);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Menu()).commit();
    }

    @Override
    public void LogoutPerform() {
        preConfig.writeLoginStatus(false);
        preConfig.writeName("User");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Login()).commit();
    }

    @Override
    public void ExportPerform() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Export()).commit();
    }

    @Override
    public void ImportPerform() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Import()).commit();
    }

    @Override
    public void CheckPerform() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Check()).commit();
    }


    @Override
    public void AddMorePerform() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddMore()).commit();
    }

    @Override
    public void AddingPerform() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Adding()).commit();
    }

    @Override
    public void GoToExportSuccess() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ExportSuccess()).commit();
    }

    @Override
    public void GoAddMore() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddMore()).commit();
    }

    @Override
    public void ImportSuccessPerform() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ImportSuccess()).commit();
    }

    @Override
    public void GoBackMenuPerform() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Menu()).commit();
    }

    @Override
    public void performCheck(String bID, String title, String genre, String pYear, String qty, String price) {
        preConfig.writeBID(bID);
        preConfig.writeTitle(title);
        preConfig.writeGenre(genre);
        preConfig.writePyear(pYear);
        preConfig.writeQty(qty);
        preConfig.writePrice(price);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ShowCheck()).commit();
    }

    @Override
    public void PerformShowCheck() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Menu()).commit();
    }

    @Override
    public void ReturnToMenu() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Menu()).commit();
    }
}
