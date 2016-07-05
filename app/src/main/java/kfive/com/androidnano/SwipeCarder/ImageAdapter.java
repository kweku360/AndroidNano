package kfive.com.androidnano.SwipeCarder;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import kfive.com.androidnano.R;

/**
 * Created by kweku on 7/4/2016.
 */
public class ImageAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Drawable> imgarr;
    public ImageAdapter(ArrayList<Drawable> arrimgs,Activity con) {
        context = con;
        imgarr = arrimgs;
    }

    @Override
    public int getCount() {
        return imgarr.size();
    }

    @Override
    public Drawable getItem(int position) {
        return imgarr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //manually set the layout inflater
            convertView = context.getLayoutInflater().inflate(R.layout.swipeitem, parent, false);
        }
        ImageView swipeimg = (ImageView)convertView.findViewById(R.id.swipeimg);
        swipeimg.setImageDrawable(getItem(position));
        return convertView;
    }
}
