package code.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import fragments.fav_fragment;
import fragments.home_fragment;
import fragments.map_fragment;

import static ClassLibrary.Changer.ReplaceFragment;

@SuppressWarnings("ALL")
public class MapActivity extends AppCompatActivity {

    //variables
    private ImageButton btn_fav;
    private ImageButton btn_map;
    private ImageButton btn_home;
    private Button btn_add;
    public static boolean isTracking;
    private map_fragment fr_map;
    private fav_fragment fr_fav;
    private home_fragment fr_home;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);

        MenuItem item_switch = menu.findItem(R.id.track_switch);
        item_switch.setActionView(R.layout.use_switch);
        final SwitchCompat sw = (SwitchCompat)menu.findItem(R.id.track_switch).getActionView().findViewById(R.id.action_switch);
         sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked){
                     Log.d("switchLog:","on");
                     isTracking = true;
                     runtime_permissions();
                     Intent i= new Intent(getApplicationContext(), Gps_Service.class);
                     startService(i);
                 }else{
                     Log.d("switchLog:","off");
                     isTracking = false;
                     Intent i= new Intent(getApplicationContext(), Gps_Service.class);
                     stopService(i);
                 }
             }
         });
        return true;
    }

    private boolean runtime_permissions() {
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},710);
            return  true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        btn_fav = (ImageButton) findViewById(R.id.btn_fav);
        btn_home = (ImageButton) findViewById(R.id.btn_home);
        btn_map = (ImageButton) findViewById(R.id.btn_map);
        btn_add = (Button)findViewById(R.id.btn_add);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_up_to_down,R.anim.exit_to_down);
        fragmentTransaction.replace(R.id.fragment_container, new map_fragment(),"map").commit();


        //change_fragment(new map_fragment(),"map");

        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //change_fragment(new fav_fragment(),"fav");
                fragmentTransaction.replace(R.id.fragment_container, new fav_fragment(),"fav").commit();
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change_fragment(new home_fragment(),"home");
                fragmentTransaction.replace(R.id.fragment_container, new home_fragment(),"home").commit();
            }
        });

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change_fragment(fr_map,"map");
                fragmentTransaction.replace(R.id.fragment_container, new map_fragment(),"map").commit();
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent i= new Intent(getApplicationContext(), Gps_Service.class);
        stopService(i);
    }


}
