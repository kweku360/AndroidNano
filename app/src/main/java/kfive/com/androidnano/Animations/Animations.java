package kfive.com.androidnano.Animations;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import kfive.com.androidnano.R;
import kfive.com.androidnano.firebase.SimpleSignin;

public class Animations extends AppCompatActivity {

    ImageView imgv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations);
        imgv = (ImageView)findViewById(R.id.imageView3);
    }
    public void showsign(View v){
        Intent intent = new Intent(this,SimpleSignin.class);
        startActivity(intent);
    }

    public void animateView(View v){
        ObjectAnimator anim;
        //Alpha
//        if(imgv.getAlpha() == 1){
//            anim = ObjectAnimator.ofFloat(imgv, View.ALPHA,0);
//        }else{
//             anim = ObjectAnimator.ofFloat(imgv, View.ALPHA,1);
//        }
        //rotate
//        anim = ObjectAnimator.ofFloat(imgv, View.ROTATION,360);
//        anim.setRepeatCount(1);
//        anim.setRepeatMode(ValueAnimator.REVERSE);
//
        anim = ObjectAnimator.ofFloat(imgv, View.TRANSLATION_Y, 100);
        anim.setRepeatCount(1);
        anim.setRepeatMode(ValueAnimator.REVERSE);


        anim.start();
    }
}
