package kfive.com.androidnano.Animations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
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
    int y=0,y1=4,y2=3,y3=2,y4=1;

    public CanvasDrawer(Context context) {
        super(context);
        sHolder = getHolder();
        setZOrderOnTop(true);
        sHolder.setFormat(PixelFormat.TRANSLUCENT);
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


            //lets do some maths
            loc_x = w / 5;
//
            Paint p = new Paint();
            p.setColor(Color.BLUE);
            p.setStyle(Paint.Style.FILL);
            canvas.drawRect(0, 0, w, h, p);

            if(loc_y > h){
                loc_y = 10;
                y=(y>4)?0:y+1;

            }else {
                loc_y = loc_y + 7;
                canvas.drawBitmap(getRandom(y), 10, loc_y, null);
            }
            if(loc_y2 > h){
                loc_y2 = 10;
                y2=(y2>4)?0:y2+1;
            }else {
                loc_y2 = loc_y2 + 9;
                canvas.drawBitmap(getRandom(y2), loc_x * 2, loc_y2, null);

            }
            if(loc_y3 > h){
                loc_y3 = 10;
                y3=(y3>4)?0:y3+1;
            }else {
                loc_y3 = loc_y3 + 5;
                canvas.drawBitmap(getRandom(y3), loc_x * 4, loc_y3, null);
            }
            if(loc_y1 > h){
                loc_y1 = 10;
                y1=(y1>1)?0:y1+1;
            }else {
                loc_y1 = loc_y1 + 11;
                canvas.drawBitmap(getRandom(y1), loc_x * 3, loc_y1, null);
            }
            if(loc_y4 > h){
                loc_y4 = 10;
                y4=(y4>4)?0:y4+1;
            }else {
                loc_y4 = loc_y4 + 13;
                canvas.drawBitmap(getRandom(y4), loc_x * 1, loc_y4, null);
            }


            sHolder.unlockCanvasAndPost(canvas);

        }
    }

    public Bitmap getRandom(int val) {
        if(val==5){
            val=0;
        }
        Bitmap[] array = {dove,hearts,kiss,love,bear};
        return array[val];
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
