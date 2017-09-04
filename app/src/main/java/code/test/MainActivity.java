package code.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ClassLibrary.ClassLibrary;
import fragments.fragment_login;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        getSupportActionBar().hide();
        ClassLibrary.ReplaceFragment(R.id.log_container,new fragment_login(),getSupportFragmentManager(),false);

        findViewById(R.id.btn_anonim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_activity();
            }
        });



    }

    void change_activity(){
        ClassLibrary.GoToActivity( this , MapActivity.class);
    }

}
