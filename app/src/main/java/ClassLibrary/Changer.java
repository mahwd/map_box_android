package ClassLibrary;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import code.test.R;

/**
 * Created by Hesen on 9/2/2017.
 */

public class Changer {

    private static Fragment last_fragment;
    private static boolean first_time;

    public static void ReplaceFragment(int resource, Fragment f, FragmentManager fm,String back_stack){
        FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.anim.slide_from_right, R.anim.exit_to_left);
                ft.replace(resource, f).addToBackStack(back_stack).commit();
                last_fragment = f;
    }
    public static void ReplaceFragment(int resource, Fragment f, FragmentManager fm,boolean save_to_back_stack){

        // Clear back_stack
        if (!save_to_back_stack){
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.exit_to_left);
        ft.replace(resource,f).addToBackStack(null).commit();
    }

    public static void GoToActivity(AppCompatActivity act,Class act_type){
        Intent i =new Intent(act,act_type);
        act.startActivity(i);
        act.finish();
    }
}
