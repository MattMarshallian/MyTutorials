package com.example.tutorial.marcin.manggha1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marcin on 23.02.2016.
 */
public class CoverFlowAdapter extends BaseAdapter {

    private ArrayList<ExhibitionEntry> mData = new ArrayList<>(0);
    private Context mContext;

    public CoverFlowAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<ExhibitionEntry> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_coverflow, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) rowView.findViewById(R.id.label);
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.image);
            viewHolder.textViewDescription = (TextView) rowView.findViewById(R.id.description);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();

        holder.imageView.setImageResource(mData.get(position).imageResId);
        holder.textView.setText(mData.get(position).titleResId);
        holder.textViewDescription.setText(mData.get(position).description);

        return rowView;
    }

    private static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public TextView textViewDescription;
    }
}
