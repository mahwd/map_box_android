package code.test;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import fragments.fragment_login;
import static ClassLibrary.Changer.GoToActivity;
import static ClassLibrary.Changer.ReplaceFragment;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        getSupportActionBar().hide();
        ReplaceFragment(R.id.log_container,new fragment_login(),getSupportFragmentManager(),false);
        findViewById(R.id.btn_anonim).setOnClickListener(v -> change_activity());



    }



    void change_activity(){
        GoToActivity( this , MapActivity.class);
    }

}
