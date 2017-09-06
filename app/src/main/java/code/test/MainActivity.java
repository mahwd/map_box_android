package code.test;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import fragments.fragment_login;
import fragments.map_fragment;

import static ClassLibrary.Changer.GoToActivity;
import static ClassLibrary.Changer.ReplaceFragment;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        getSupportActionBar().hide();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_up_to_down,R.anim.exit_to_down);
        fragmentTransaction.replace(R.id.log_container, new fragment_login(),"log_in").commit();
        findViewById(R.id.btn_anonim).setOnClickListener(v ->
        {
            Intent i = new Intent(this,MapActivity.class);
            startActivity(i);
        });



    }



    void change_activity(){
        GoToActivity( this , MapActivity.class);
    }

}
