package com.example.gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Homa extends Fragment{
	ImageView imgCar;
	ImageView imgFer;
	ImageView imgElec;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.home, container, false);
		
	
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		imgCar = (ImageView) view.findViewById(R.id.carImg);
		imgFer = (ImageView) view.findViewById(R.id.ferImg);
		imgElec = (ImageView) view.findViewById(R.id.elecImg);
	}
}
