package kfive.com.androidnano.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import kfive.com.androidnano.R;

public class SimpleSignin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    SignInButton sbtn;
    GoogleApiClient gClient;
    private static final int RC_SIGN_IN = 9001; //intent result code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_signin);
        sbtn = (SignInButton)findViewById(R.id.an_signin);
//        create a gogole signin options class
        GoogleSignInOptions Gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,Gso)
                .build();

        eventMethods();
    }

    private void eventMethods() {
        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent gSignInIntent = Auth.GoogleSignInApi.getSignInIntent(gClient);
        startActivityForResult(gSignInIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            GoogleSignInResult gResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(gResult);
        }
    }

    private void handleSignInResult(GoogleSignInResult gResult) {
        if(gResult.isSuccess()){
            GoogleSignInAccount gAccount = gResult.getSignInAccount();
            Toast.makeText(this,gAccount.getEmail(),Toast.LENGTH_LONG).show();
        }else{

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
