package kfive.com.androidnano.AppLister;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kfive.com.androidnano.R;

/**
 * Created by kweku on 6/10/2016.
 */
public class ApplistAdapter extends BaseAdapter {
    List<ApplicationInfo> appInfo;
    private Activity appContext;
    int count = 0;
    PackageManager pm;
    public ApplistAdapter(List<ApplicationInfo> appinfo, Activity context) {
        appInfo = appinfo;
        appContext = context;
        pm = context.getPackageManager();
    }

    @Override
    public int getCount() {
        return appInfo.size();
    }

    @Override
    public ApplicationInfo getItem(int position) {
        return appInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            //manually set the layout inflater
            convertView = appContext.getLayoutInflater().inflate(R.layout.applister_listitem, parent, false);
        }
        TextView appname = (TextView) convertView.findViewById(R.id.al_appname);
        ImageView appicon = (ImageView) convertView.findViewById(R.id.al_appicon);

        ApplicationInfo appitem = getItem(position);
        appname.setText(pm.getApplicationLabel(appitem));
        appicon.setImageDrawable(pm.getApplicationIcon(appitem));

        return convertView;
    }

}

