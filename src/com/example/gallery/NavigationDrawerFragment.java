package com.example.gallery;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NavigationDrawerFragment extends Fragment {

	public ListView mDrawerListView;
	private DrawerLayout mDrawerLayout = null;
	private ActionBarDrawerToggle mDrawerToggle;
	private View mFragmentContainerView;
	boolean userOpenDrawer;
	boolean mSavedInstanceState;
	private NavigationDrawerCallbacks mCallbacks;
	private int mCurrentSelectedPosition = 0;
	CustomDrawerAdapter adapter;
	List<DrawerItem> dataList;
	AdvertiseFrg adverFragment;
	List<SGItem> ls;
	final String STATE_SELECTED_POSITION= "selected_navigation_drawer_position";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			mSavedInstanceState = true;
			//selectItem(0);
		}
		if (userOpenDrawer) {
			userOpenDrawer = false;
		}

	   
	    
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mDrawerListView = (ListView) inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
		mDrawerListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				selectItem(position);
			}
		});
		dataList = new ArrayList<DrawerItem>();
		dataList.add(new DrawerItem(R.string.item1));
		dataList.add(new DrawerItem(R.string.item2));
		dataList.add(new DrawerItem(R.string.item3));

		// List<DrawerItem> items = new String[]{getString(R.string.item1),
		// getString(R.string.item2), getString(R.string.item3)};
		adapter = new CustomDrawerAdapter(getActivity(), R.layout.fragment_main, dataList);
		mDrawerListView.setAdapter(adapter);
		 mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);

		return mDrawerListView;

	}

	private ActionBar getActionBar() {
		return ((AppCompatActivity) getActivity()).getSupportActionBar();
	}

	public boolean isDrawerOpen() {
		return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
	}

	public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {

		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;
		ActionBar actionBar = getActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);

		actionBar.setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
				if (!userOpenDrawer) {
					userOpenDrawer = true;
				}
				getActivity().invalidateOptionsMenu();

			}

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
				getActivity().invalidateOptionsMenu();
			}
		};

		if (!userOpenDrawer && !mSavedInstanceState) {
			mDrawerLayout.openDrawer(mFragmentContainerView);
		}
		mDrawerLayout.addDrawerListener(mDrawerToggle);

		mDrawerLayout.post(new Runnable() {
			public void run() {
				mDrawerToggle.syncState();
			}
		});
	}

	public void selectItem(int position) {
		Fragment fragment = null;
		switch(position){
		
		case 0:

			fragment = new Homa();
			
			break;
		
		case 1: 
			fragment = new AdvertiseFrg();
		
			break;
			
		case 2: 
		
			fragment = new ListItems();

			
			
		}
		
		if(fragment!= null){
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
			 mDrawerListView.setItemChecked(position, true);
	            mDrawerListView.setSelection(position);
	            
	            mDrawerLayout.closeDrawer(mDrawerListView);
		}
			
	}

	@Override
	public void onDestroy() {
		FragmentManager fm = getFragmentManager();
		Fragment XmlFragment = fm.findFragmentById(R.id.navigation_drawer);
		if (XmlFragment != null) {
			fm.beginTransaction().remove(XmlFragment).commit();
		}
		super.onDestroy();
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		mCallbacks = null;
	}

	public static interface NavigationDrawerCallbacks {
		void onNavigationDrawerItemSelected(int position);

	}

}