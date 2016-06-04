package com.example.gallery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class AdvertiseFrg extends Fragment implements OnClickListener, OnItemSelectedListener {
	File path;
	Bitmap bmp;
	ImageView img;

	Button btnSel;
	Button btnSave;

	private String category;
	private String img_path;
	private int price;
	private String title;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		btnSel = (Button) view.findViewById(R.id.btnSelect);
		btnSave = (Button) view.findViewById(R.id.btnUp);
		img = (ImageView) view.findViewById(R.id.imageView1);
		Spinner spin = (Spinner) view.findViewById(R.id.spinner);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Spinner,
				android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);
		spin.setOnItemSelectedListener(this);
		btnSel.setOnClickListener(this);
		btnSave.setOnClickListener(this);

		EditText txtTitle = (EditText) view.findViewById(R.id.titletxt);
		EditText txtPrice = (EditText) view.findViewById(R.id.pricetxt);
		title = txtTitle.getText().toString();
		price = txtPrice.getInputType();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.upload, container, false);
		return view;

	}

	private void saveImage(Bitmap bit) {
		String f = Environment.getExternalStorageDirectory().toString();
		path = new File(f + "/saved_image");
		path.mkdir();
		Random generator = new Random();
		int i = 1000;
		i = generator.nextInt(i);
		String fname = "image-" + i + ".jpg";
		File file = new File(path, fname);
		img_path = path.toString() + fname;
		try {
			OutputStream out = new FileOutputStream(file);
			bit.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != getActivity().RESULT_CANCELED) {
			if (requestCode == 2) {

				Bundle extra = data.getExtras();
				if (data.getData() == null) {
					bmp = (Bitmap) data.getExtras().get("data");
				} else {
					try {
						bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				img.setImageBitmap(bmp);

				saveImage(bmp);
			}
		}
	}

	private void SaveItem() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnSelect:
			Intent intent = new Intent(Intent.ACTION_PICK);
			intent.setType("video/, images/");
			startActivityForResult(intent, 2);
			break;

		case R.id.btnUp:
			SGItem items = new SGItem();
			items.setImg_path(img_path);
			items.setPrice(price);
			items.setDiscription(title);
			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		SGItem items = new SGItem();
		items.setCategory(category);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}
