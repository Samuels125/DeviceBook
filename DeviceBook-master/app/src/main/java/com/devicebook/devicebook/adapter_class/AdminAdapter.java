package com.devicebook.devicebook.adapter_class;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.devicebook.devicebook.R;
import com.devicebook.devicebook.activities.AdminActivity;
import com.devicebook.devicebook.database.MyDatabase;
import com.devicebook.devicebook.objects.AdminObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 25/04/2018.
 */

public class AdminAdapter extends ArrayAdapter<AdminObject> implements Filterable{
    private Context cscontext;
    int csresource;
    LayoutInflater myinflater;
    MyDatabase dbhelper;
    TextView tvuserName1;
    TextView tvdeviceType1;
    TextView tvdeviceModel1;
    TextView tvdeviceFault1;
    private ItemFilter mFilter = new ItemFilter();
    TextView mobileorder;
    TextView tabletorder;
    TextView totalorder;
    private List<String>originalData = null;
    private List<String>filteredData = null;

    public AdminAdapter( Context context, int resource,  List<AdminObject> objects) {
        super(context, resource, objects);
        cscontext = context;
        csresource = resource;


    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        myinflater = LayoutInflater.from(cscontext);
        convertView = myinflater.inflate(csresource, parent, false);


        dbhelper = new MyDatabase(convertView.getContext());

        dbhelper.getWritableDatabase();




        String userName1 = getItem(position).getUserName1();
        String deviceType1 = getItem(position).getDeviceType1();
        String deviceModel1 = getItem(position).getDeviceModel1();
        String deviceFault1 = getItem(position).getDeviceFault1();


        tvuserName1 = convertView.findViewById(R.id.userName1);
        tvdeviceType1 = convertView.findViewById(R.id.deviceType1);
        tvdeviceModel1 = convertView.findViewById(R.id.deviceModel1);
        tvdeviceFault1 = convertView.findViewById(R.id.deviceFault1);

        tvuserName1.setText(userName1);
        tvdeviceType1.setText( deviceType1);
        tvdeviceModel1.setText(deviceModel1);
        tvdeviceFault1.setText(deviceFault1);

        return convertView;
    }
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<String> list = originalData ;

            int count = list.size();
            final ArrayList<String> nlist = new ArrayList<String>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }

    }

}
