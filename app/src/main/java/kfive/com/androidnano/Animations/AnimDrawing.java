package kfive.com.androidnano.Animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import kfive.com.androidnano.R;

public class AnimDrawing extends AppCompatActivity {

    CanvasDrawer cDrawer;
    LinearLayout lay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cDrawer = new CanvasDrawer(this);
        setContentView(R.layout.animlayout);

        lay = (LinearLayout)findViewById(R.id.llay);
        lay.addView(cDrawer);
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
