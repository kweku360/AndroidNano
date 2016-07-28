package kfive.com.androidnano.Animations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

import kfive.com.androidnano.R;

/**
 * Created by kweku on 7/28/2016.
 */
public class CanvasDrawer extends SurfaceView implements Runnable {

    Thread thread = null;
    boolean canDraw = false;

    Bitmap dove, love, kiss, hearts, bear;
    Canvas canvas;
    SurfaceHolder sHolder;

    float loc_x = 1;
    int loc_y = 1, loc_y1 = 1, loc_y2 = 1, loc_y3 = 1, loc_y4 = 1;

    public CanvasDrawer(Context context) {
        super(context);
        sHolder = getHolder();
        dove = BitmapFactory.decodeResource(getResources(), R.drawable.an_dove);
        love = BitmapFactory.decodeResource(getResources(), R.drawable.an_love);
        hearts = BitmapFactory.decodeResource(getResources(), R.drawable.an_hearts);
        bear = BitmapFactory.decodeResource(getResources(), R.drawable.an_bear);
        kiss = BitmapFactory.decodeResource(getResources(), R.drawable.an_kiss);

    }

    @Override
    public void run() {
        while (canDraw) {
            if (!sHolder.getSurface().isValid()) {
                continue;
            }
            canvas = sHolder.lockCanvas();

            DisplayMetrics metrics = getResources().getDisplayMetrics();
            float w = metrics.widthPixels;
            float h = metrics.heightPixels;

//            loc_y = (loc_y > h)? 10:loc_y + getRandom();
//            loc_y1 = (loc_y1 > h)? 10:loc_y1 + getRandom();
//            loc_y2 = (loc_y2 > h)? 10:loc_y2 + getRandom();
//            loc_y3 = (loc_y3 > h)? 10:loc_y3 + getRandom();
//            loc_y4 = (loc_y4 > h)? 10:loc_y4 + getRandom();

//            loc_y = (loc_y > h)? 10:loc_y + 7;
//            loc_y1 = (loc_y1 > h)? 10:loc_y1 + 9;
//            loc_y2 = (loc_y2 > h)? 10:loc_y2 + 5;
//            loc_y3 = (loc_y3 > h)? 10:loc_y3 + 11;
//            loc_y4 = (loc_y4 > h)? 10:loc_y4 + 13;

            if(loc_y > h){
                loc_y = 10;
                dove = getRandom();
            }else {
               loc_y = loc_y + 7;
            }
            if(loc_y2 > h){
                loc_y2 = 10;
                love = getRandom();
            }else {
               loc_y2 = loc_y2 + 9;
            }
            if(loc_y3 > h){
                loc_y3 = 10;
                kiss = getRandom();
            }else {
               loc_y3 = loc_y3 + 5;
            }
            if(loc_y1 > h){
                loc_y1 = 10;
                hearts = getRandom();
            }else {
               loc_y1 = loc_y1 + 11;
            }
            if(loc_y4 > h){
                loc_y4 = 10;
                bear = getRandom();
            }else {
               loc_y4 = loc_y4 + 13;
            }

            //lets do some maths
            loc_x = w / 5;

            Paint p = new Paint();
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.FILL);
            canvas.drawRect(0, 0, w, h, p);

            canvas.drawBitmap(dove, 10, loc_y, null);
            canvas.drawBitmap(hearts, loc_x * 1, loc_y4, null);
            canvas.drawBitmap(love, loc_x * 2, loc_y2, null);
            canvas.drawBitmap(kiss, loc_x * 3, loc_y1, null);
            canvas.drawBitmap(bear, loc_x * 4, loc_y3, null);

            sHolder.unlockCanvasAndPost(canvas);

        }
    }

    public Bitmap getRandom() {
        Bitmap[] array = {dove,hearts,kiss,love,bear};
        int rnd = new Random().nextInt(array.length);
        Log.v("randomval",String.valueOf(rnd));
        return array[rnd];
    }
//
//    private float yval(int i) {
//
//    }


    public void resume() {
        canDraw = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        canDraw = false;
        while (true) {
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = null;
    }
}
