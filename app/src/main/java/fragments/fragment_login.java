package fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import code.test.MapActivity;
import code.test.R;
import static ClassLibrary.Changer.GoToActivity;
import static ClassLibrary.Changer.ReplaceFragment;

public class fragment_login extends Fragment{

    //variables

    private EditText edt_login;
    private EditText edt_pass;
    private TextView txt_sign_up;
    private Button btn_log_in;

    private FirebaseAuth firebase_auth;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_fragment, container, false);
        edt_login = (EditText) view.findViewById(R.id.edt_username);
        edt_pass = (EditText) view.findViewById(R.id.edt_pass);
        txt_sign_up = (TextView)view.findViewById(R.id.txt_sign_up);
        btn_log_in = (Button) view.findViewById(R.id.btn_login);

        firebase_auth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("please wait");

        txt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Debug login 1",Toast.LENGTH_SHORT).show();
                ReplaceFragment(R.id.log_container,new fragment_sign_up(),getActivity().getSupportFragmentManager(),false);
                Toast.makeText(getContext(),"Debug login 2",Toast.LENGTH_LONG).show();
            }
        });
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                final String email = edt_login.getText().toString();
                final String pass = edt_pass.getText().toString();
                if (!TextUtils.isEmpty(email) &&
                        !TextUtils.isEmpty(pass)){
                    firebase_auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getContext(),"sign in successful",Toast.LENGTH_SHORT).show();
                                GoToActivity((AppCompatActivity) getActivity(), MapActivity.class);
                                pd.dismiss();
                            }
                            else{
                                    if (!email.contains("@") || !email.contains(".")){
                                        Toast.makeText(getContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                                    } else if (pass.length()<6){
                                        Toast.makeText(getContext(),"Invalid password. Password should be at least 6 characters",Toast.LENGTH_SHORT).show();
                                    }
                                    else if (task.getException() !=  null){
                                        Toast.makeText(getContext(),task.getException().toString(),Toast.LENGTH_SHORT).show();
                                    }
                                    pd.dismiss();

                            }
                        }
                    });
                }
            }
        });
    }
}