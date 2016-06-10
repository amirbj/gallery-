package com.example.gallery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.R.array;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.graphics.BitmapCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListItems extends ListFragment implements OnClickListener {

	TextView tvPrice;
	private Context context;
	public List<SGItem> listItem;
	ListView listview;

	TextView tvTitle;
	TextView tvItem;
	ImageView imgItem;
	View view;
	Button btnDel;

	public void display() {

		ArrayAdapter adapter = new ItemAdapter(getActivity().getApplicationContext(), R.layout.list_main, listItem);
		setListAdapter(adapter);

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		// listview = (ListView) view.findViewById(R.id.list_main);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// View view = inflater.inflate(R.layout.list_item, container, false);
		// SGItem item = new SGItem();
		// tvPrice = (TextView) view.findViewById(R.id.pricetv);
		// tvTitle = (TextView) view.findViewById(R.id.titletv);
		// tvItem = (TextView) view.findViewById(R.id.categorytv);
		// imgItem = (ImageView) view.findViewById(R.id.imglist);
		// btnDel = (Button) view.findViewById(R.id.btnDel);
		// btnDel.setOnClickListener(this);
		listItem = new ArrayList<>();
		activitytask mytask = new activitytask();
		mytask.execute();
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public Bitmap getImage(String path) {
		Bitmap thumbnail = null;

		try {
			if (path != null) {

				File imgFile = new File(path);
				{

					if (imgFile.exists()) {
						FileInputStream fi = new FileInputStream(path);
						thumbnail = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

					}
				}
			}
			return thumbnail;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public List<SGItem> getItem(Cursor cur) {
		try {
			List<SGItem> items = new ArrayList<>();

			if (cur == null) {
				Toast.makeText(getActivity(), "there is no data to ritrieve", Toast.LENGTH_LONG).show();
			} else {

				while (cur.moveToNext()) {
					SGItem item = new SGItem();

					item.setCategory(cur.getString(cur.getColumnIndex("category")));
					item.setPrice(cur.getInt(cur.getColumnIndex("price")));
					item.setDiscription(cur.getString(cur.getColumnIndex("discription")));
					item.setImg_path(cur.getString(cur.getColumnIndex("img_path")));

					items.add(item);
				}

			}

			return items;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// TODO: handle exception
		finally {
			cur.close();

		}
	}

	public class activitytask extends AsyncTask<Void, Void, Cursor> {

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			ProgressDialog progress = new ProgressDialog(getActivity(), R.style.Animation_AppCompat_Dialog);
			progress.setIndeterminate(true);
			progress.setMessage("getting data....");
			progress.show();
			super.onProgressUpdate(values);
		}

		@Override
		protected Cursor doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Database db = new Database(getActivity().getApplicationContext());
			Cursor cur = db.getItems();
			return cur;

		}

		@Override
		protected void onPostExecute(Cursor result) {

			listItem = getItem(result);

			display();

			// TODO Auto-generated method stub

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		new android.os.Handler().postDelayed(new Runnable() {
			public void run() {
				Database db = new Database(getActivity().getApplicationContext());
				int id = db.delete();
				Toast.makeText(getActivity().getApplicationContext(), id + " rows has been deleted", Toast.LENGTH_LONG)
						.show();

			}
		}, 1000);
	}
}
