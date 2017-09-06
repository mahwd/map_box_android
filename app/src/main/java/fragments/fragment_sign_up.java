package fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import Model.User;
import code.test.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class fragment_sign_up extends Fragment {

    private EditText edt_name;
    private EditText edt_email;
    private EditText edt_age;
    private EditText edt_user_name;
    private EditText edt_pass;
    private Button btn_sign_up;
    private FirebaseAuth _fa;
    private FirebaseUser _user;
    private TextView txt_log_in;
    String name;
    String user_name;
    String email;
    int age;
    String pass;
    DatabaseReference users;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users=FirebaseDatabase.getInstance().getReference();
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_up_to_down,R.anim.exit_to_down);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_fragment,container,false);

        edt_name = (EditText) view.findViewById(R.id.edt_name);
        edt_email = (EditText) view.findViewById(R.id.edt_email);
        edt_age = (EditText) view.findViewById(R.id.edt_birth);
        edt_user_name = (EditText) view.findViewById(R.id.edt_username);
        edt_pass = (EditText) view.findViewById(R.id.edt_pass);
        txt_log_in = (TextView) view.findViewById(R.id.txt_sign_in);
        btn_sign_up = (Button) view.findViewById(R.id.btn_sign_up);

        _fa = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        btn_sign_up.setOnClickListener(v -> {
            final ProgressDialog pd;
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
            name = edt_name.getText().toString();
            email = edt_email.getText().toString();
            pass = edt_pass.getText().toString();
            user_name = edt_user_name.getText().toString();
            age = Integer.parseInt(edt_age.getText().toString());
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(pass)){
                Toast.makeText(getContext(),"fill all blanks", Toast.LENGTH_SHORT).show();
            }else {
                Log.d("Email => ",email);
                _fa.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                         //createing new user
                        createNewUser(task.getResult().getUser(), name, user_name, age );
                        fragmentTransaction.replace(R.id.log_container, new fragment_login(),"sign_up").commit();
                        Toast.makeText(getContext(),"Your sign up completed successfully",Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }else {
                        if (!email.contains("@") && !email.contains("."))
                            Toast.makeText(getContext(),"Invalid email.",Toast.LENGTH_LONG).show();
                        if (pass.length()<6)
                            Toast.makeText(getContext(),"Invalid password. Password should be at least 6 characters ",Toast.LENGTH_LONG).show();
                        if (task.getException() != null)
                            Toast.makeText(getContext(),task.getException().toString(),Toast.LENGTH_LONG).show();
                        pd.dismiss();
                    }
                });
           }
    });


        txt_log_in.setOnClickListener(v -> fragmentTransaction.replace(R.id.log_container, new fragment_login(),"log_in").commit());
    }


    private void createNewUser(FirebaseUser userFromRegistration,String name, String username, int age) {

        String email = userFromRegistration.getEmail();
        String userId = userFromRegistration.getUid();
        User user = new User(userId, name, username, email,age);

        users.child("users").child(userId).setValue(user);

    }
}

