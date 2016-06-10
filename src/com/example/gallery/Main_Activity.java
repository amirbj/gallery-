package com.example.gallery;


import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class Main_Activity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private Toolbar toolbar;
	private NavigationDrawerFragment mNavigationDrawerFragment;

	CustomDrawerAdapter adapter;
	List<DrawerItem> dataList;
	CharSequence mTitle;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_app);
		toolbar = (Toolbar) findViewById(R.id.app_bar);
		toolbar.setTitle(R.string.toolbar_title);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout),
				toolbar);
		
		

	}



	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// TODO Auto-generated method stub
		
	}
	
	



	}

