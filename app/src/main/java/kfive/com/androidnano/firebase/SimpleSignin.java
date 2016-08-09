package kfive.com.androidnano.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import kfive.com.androidnano.R;

public class SimpleSignin extends AppCompatActivity {

    SignInButton sbtn;
    GoogleApiClient gClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_signin);
        sbtn = (SignInButton)findViewById(R.id.an_signin);
//        create a gogole signin options class
        GoogleSignInOptions Gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gClient = new GoogleApiClient.Builder(this).enableAutoManage(this,0,null);
    }
}
