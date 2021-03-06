package kfive.com.androidnano.SwipeCarder;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import kfive.com.androidnano.R;

public class SwipeCarderActivity extends AppCompatActivity {

   // ImageView textimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_carder);
       // textimg = (ImageView)findViewById(R.id.imageView);
        showSwipeCard();
    }

    private void showSwipeCard() {
        final SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.swipe_frame);

        final ArrayList al = new ArrayList<Drawable>();
        al.add(getResources().getDrawable(R.drawable.a1));
        al.add(getResources().getDrawable(R.drawable.a2));
        al.add(getResources().getDrawable(R.drawable.a3));
        al.add(getResources().getDrawable(R.drawable.a4));
        al.add(getResources().getDrawable(R.drawable.a5));

        //choose your favorite adapter
        final ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.swipeitem, R.id.swipeimg, al );
        final ImageAdapter imageAdapter = new ImageAdapter(al,this);

        //set the listener and the adapter
        flingContainer.setAdapter(imageAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                Toast.makeText(SwipeCarderActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object o) {
                Toast.makeText(SwipeCarderActivity.this, "right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                // Ask for more data here
                al.add(getResources().getDrawable(R.drawable.a4));
                imageAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float v) {

                Log.d("Scr", String.valueOf(v));
                if(v < 0){
                    flingContainer.getSelectedView().findViewById(R.id.imageView).setAlpha(0);
                    flingContainer.getSelectedView().findViewById(R.id.imageView2).setAlpha(-(v));
                }else{
                    flingContainer.getSelectedView().findViewById(R.id.imageView2).setAlpha(0);
                    flingContainer.getSelectedView().findViewById(R.id.imageView).setAlpha(v);
                }

            }
        });

    }
}
