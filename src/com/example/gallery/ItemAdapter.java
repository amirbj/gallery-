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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemAdapter extends ArrayAdapter<SGItem> {

	private Context context;
	private List<SGItem> listItem;
	TextView tvTitle;
	TextView tvItem;
	ImageView imgItem;
	TextView tvPrice;
	Button btnDel;

	public ItemAdapter(Context con, int resource, List<SGItem> objects) {
		super(con, resource, objects);

		this.context = con;
		this.listItem = objects;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		 ViewHolder holder;
		 final int delId;

		holder = new ViewHolder();
		View RootView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
		
		holder.tvPrice = (TextView) RootView.findViewById(R.id.pricetv);
		holder.tvTitle = (TextView) RootView.findViewById(R.id.titletv);
		holder.tvItem = (TextView) RootView.findViewById(R.id.categorytv);
		holder.imgItem = (ImageView) RootView.findViewById(R.id.imglist);
		 holder.btnDel = (Button) RootView.findViewById(R.id.btnDel);

		holder.item = listItem.get(position);
		holder.tvPrice.setText("" + holder.item.getPrice());
		holder.tvItem.setText(holder.item.getCategory());
		holder.tvTitle.setText(holder.item.getDiscription());
		ListItems ls = new ListItems();
		Bitmap bit = ls.getImage(holder.item.getImg_path());
		delId = holder.item.getId();
		
		 holder.btnDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new android.os.Handler().postDelayed(new Runnable() {
					public void run() {
						
						Database db = new Database(context);
						int id = db.delete(delId);
						Log.e("adapter id", "ths is "+ Integer.toString(delId));
						Toast.makeText(context, id + " rows has been deleted", Toast.LENGTH_LONG)
								.show();

					}
				}, 1000);
				
			}
		});

		holder.imgItem.setImageBitmap(bit);
		
		return RootView;
	}

	class ViewHolder {
		SGItem item;
		TextView tvTitle;
		TextView tvItem;
		ImageView imgItem;
		TextView tvPrice;
		Button btnDel;
	}

}
