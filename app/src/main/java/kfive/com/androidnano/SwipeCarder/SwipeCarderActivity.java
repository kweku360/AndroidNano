package kfive.com.androidnano.SwipeCarder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import kfive.com.androidnano.R;

public class SwipeCarderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_carder);
        showSwipeCard();
    }

    private void showSwipeCard() {
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.swipe_frame);

        final ArrayList al = new ArrayList<String>();
        al.add("php");
        al.add("c");
        al.add("python");
        al.add("java");

        //choose your favorite adapter
        final ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.swipeitem, R.id.swipetxt, al );

        //set the listener and the adapter
        flingContainer.setAdapter(arrayAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
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
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float v) {

            }
        });

    }
}
