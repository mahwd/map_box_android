package fragments;


import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import code.test.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fav_fragment extends Fragment {

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String tag = "fav";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

}
