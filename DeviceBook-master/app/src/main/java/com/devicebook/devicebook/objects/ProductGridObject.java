package com.devicebook.devicebook.objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.devicebook.devicebook.R;

/**
 * Created by Sam on 26/01/2018.
 */

public class ProductGridObject extends BaseAdapter {

private int myimages[];

private String mycaptions[];

private Context context;

private LayoutInflater inflater;

    public ProductGridObject(Context context, int myimages[], String mycaptions[]){

        this.context = context;
        this.myimages = myimages;
        this.mycaptions = mycaptions;
    }

    @Override
    public int getCount() {
        return mycaptions.length;
    }

    @Override
    public Object getItem(int i) {
        return mycaptions [i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View myview, ViewGroup parent) {

        View gridView = myview;

        if(myview == null){

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        gridView = inflater.inflate(R.layout.custom_gridlayout,null);

        ImageView myimage = gridView.findViewById(R.id.phone_device);
        TextView mycaption = gridView.findViewById(R.id.phone_caption);

        myimage.setImageResource(myimages[i]);
        mycaption.setText(mycaptions[i]);
        return gridView;
    }
}
