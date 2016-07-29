package kfive.com.androidnano.Animations;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import kfive.com.androidnano.R;

public class Animations extends AppCompatActivity {

    ImageView imgv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);
        imgv = (ImageView)findViewById(R.id.imageView);

        ValueAnimator anim = ObjectAnimator.ofInt(imgv,"rotationX",300,100);
        anim.setDuration(2000);
        anim.start();
    }
}
