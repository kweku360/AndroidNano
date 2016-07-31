package kfive.com.androidnano.AppLister;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kfive.com.androidnano.R;

public class ApplistMain extends Activity {

    //GridView gView;
    LinearLayout gLayout;
    LinearLayout sContainer;
    String storefirstletter = "A";
    int fval = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist_main);

        //gView = (GridView) findViewById(R.id.gridView);

        gLayout = (LinearLayout) findViewById(R.id.gridLayout);x
        sContainer = (LinearLayout) findViewById(R.id.al_staticcontainer);
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        final Drawable wallpaperDrawable = wallpaperManager.getDrawable();

        //convert to drawable
        Bitmap wallpaper = ((BitmapDrawable) wallpaperDrawable).getBitmap();
        Bitmap blurredwallpaper = BlurEffect.blur(this,wallpaper);

         gLayout.setBackgroundDrawable( new BitmapDrawable( getResources(), blurredwallpaper ));

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        showAppList();
    }

    private void showAppList() {

        Log.v("Apps Count",String.valueOf(getNonSystemApps().size()));
//        ApplistAdapter appAdapter = new ApplistAdapter(getNonSystemApps(),this);
//        gView.setAdapter(appAdapter);

        final PackageManager pm = getPackageManager();
        for (ApplicationInfo appinfo : getNonSystemApps()) {

            Log.v("Apps Name",String.valueOf(pm.getApplicationLabel(appinfo)));

            //get first letter
            String fletter = pm.getApplicationLabel(appinfo).toString().substring(0,1);
            //store first letter

            //now compare
            if(storefirstletter.equals(fletter)){
                //create container with fval
                createContainer(pm.getApplicationIcon(appinfo),pm.getApplicationLabel(appinfo),fval);
             }else{
                storefirstletter = fletter;
                fval = fval +1;
            }
        }

    }

    private void createContainer(Drawable applicationIcon, CharSequence label, int fval) {
        //create only if not exists
        FlowLayout itemContainer = (FlowLayout) findViewById(fval);
        if(itemContainer == null){
            //now lets create
            itemContainer = new FlowLayout(this);
            itemContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            itemContainer.setOrientation(LinearLayout.HORIZONTAL);
            itemContainer.setId(fval);
            createUiItem(applicationIcon,label,itemContainer);
            sContainer.addView(itemContainer);
        }else{
            // lets add the views to it
            createUiItem(applicationIcon,label,itemContainer);

        }
    }

    private ArrayList<ApplicationInfo> getNonSystemApps(){
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        ArrayList<ApplicationInfo> nonsystem = new ArrayList<>();

        for (ApplicationInfo appinfo : packages) {
            if( pm.getLaunchIntentForPackage(appinfo.packageName) != null ){
                nonsystem.add(appinfo);
            }
        }
        //Sort Alphabetically
        Collections.sort(nonsystem, new Comparator<ApplicationInfo>() {

            @Override
            public int compare(ApplicationInfo first, ApplicationInfo second) {
                return pm.getApplicationLabel(first).toString().compareTo(pm.getApplicationLabel(second).toString());
            }
        } );

        return nonsystem;
    }

    private void createUiItem(Drawable applicationIcon, CharSequence applicationLabel, FlowLayout itemContainer){
        LinearLayout itemBox = new LinearLayout(this);
        ImageView appicon = new ImageView(this);
        TextView applabel = new TextView(this);

        int iheight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        int iwidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(iwidth,iheight);
        params.gravity = Gravity.CENTER;
        appicon.setLayoutParams(params);
        appicon.setImageDrawable(applicationIcon);

        applabel.setText(applicationLabel);
        applabel.setEllipsize(TextUtils.TruncateAt.END);
        applabel.setLines(1);
        applabel.setTextSize(11);
        applabel.setGravity(Gravity.CENTER);
        applabel.setTextColor(Color.WHITE);


        int Lheight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int Lwidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
        itemBox.setLayoutParams(new LinearLayout.LayoutParams(Lwidth,Lheight));
        itemBox.setOrientation(LinearLayout.VERTICAL);

        itemBox.addView(appicon);
        itemBox.addView(applabel);
        itemContainer.addView(itemBox);

    }

}
