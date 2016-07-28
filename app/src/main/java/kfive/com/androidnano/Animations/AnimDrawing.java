package kfive.com.androidnano.Animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AnimDrawing extends AppCompatActivity {

    CanvasDrawer cDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cDrawer = new CanvasDrawer(this);
        setContentView(cDrawer);
    }

    @Override
    protected void onPause() {
        super.onPause();
        cDrawer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cDrawer.resume();
    }
}
