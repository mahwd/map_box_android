package ClassLibrary;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hesen on 9/2/2017.
 */

public class ClassLibrary {


    public static void ReplaceFragment(int resource, Fragment f, FragmentManager fm){
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(resource,f).addToBackStack(null).commit();
    }
    public static void ReplaceFragment(int resource, Fragment f, FragmentManager fm,boolean save_to_back_stack){

        // Clear back_stack
        if (!save_to_back_stack){
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(resource,f).addToBackStack(null).commit();
    }

    public static void GoToActivity(AppCompatActivity act,Class act_type){
        Intent i =new Intent(act,act_type);
        act.startActivity(i);
    }
}
