package com.example.pmalv3priprema;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class AdapterProjects extends BaseAdapter {

    ArrayList<String> oItemList;
    public Activity oContext;
    public LayoutInflater oInflater;
    public AdapterProjects(Activity context, ArrayList<String> itemList)
    {
        super();
        this.oContext = context;
        this.oItemList = itemList;
        this.oInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return 0;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }


    public static class ViewHolder
    {
        TextView txtViewTitle;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder oHolder;
        if(convertView == null)
        {
            oHolder = new ViewHolder();
            convertView = oInflater.inflate(R.layout.project_item, null);
            oHolder.txtViewTitle = (TextView) convertView.findViewById(R.id.textViewProjectTitle);convertView.setTag(oHolder);
        }
        else {
            oHolder = (ViewHolder) convertView.getTag();
        }

        JSONObject oItem = null;
        try {
            oItem = new JSONObject(oItemList.get(position));
            oHolder.txtViewTitle.setText(oItem.getJSONObject("translations").getJSONObject("1").getString("title"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }


}

