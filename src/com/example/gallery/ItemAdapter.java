package com.example.gallery;

import java.util.List;
import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<SGItem> {

	private Context context;
	private List<SGItem> listItem;
	TextView tvTitle;
	TextView tvItem;
	ImageView imgItem;
	TextView tvPrice;

	public ItemAdapter(Context con, int resource, List<SGItem> objects) {
		super(con, resource, objects);

		this.context = con;
		this.listItem = objects;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder;

		holder = new ViewHolder();
		View RootView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
		
		holder.tvPrice = (TextView) RootView.findViewById(R.id.pricetv);
		holder.tvTitle = (TextView) RootView.findViewById(R.id.titletv);
		holder.tvItem = (TextView) RootView.findViewById(R.id.categorytv);
		holder.imgItem = (ImageView) RootView.findViewById(R.id.imglist);

		holder.item = listItem.get(position);
		holder.tvPrice.setText("" + holder.item.getPrice());
		holder.tvItem.setText(holder.item.getCategory());
		holder.tvTitle.setText(holder.item.getDiscription());
		ListItems ls = new ListItems();
		Bitmap bit = ls.getImage(holder.item.getImg_path());

		holder.imgItem.setImageBitmap(bit);
		
		return RootView;
	}

	class ViewHolder {
		SGItem item;
		TextView tvTitle;
		TextView tvItem;
		ImageView imgItem;
		TextView tvPrice;
	}

}
