package kfive.com.androidnano;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kfive.com.androidnano.SwipeCarder.SwipeCarderActivity;

public class NanoHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nano_home);

    }

    public void startSwipeCarder(View v) {
        Intent intent = new Intent(this, SwipeCarderActivity.class);
        startActivity(intent);
    }

}
