package com.yiqipao;

//import android.content.Context;
//import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
//import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	
	private String[] mSectionTitles;
	private PlaceholderFragment[] mFragments;
	private int mCurFragIdx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		//	set up fragments which are sitting on main panel
		{
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			
			mSectionTitles = getResources().getStringArray(R.array.title_section);			
			mCurFragIdx = 0;
			mFragments = new PlaceholderFragment[mSectionTitles.length];
			for ( int i = 0; i < mSectionTitles.length; i++ ) {
				mFragments[i] = PlaceholderFragment.newInstance(i, mSectionTitles[i]);
				fragmentTransaction.add(R.id.container, mFragments[i]);
				if ( i != mCurFragIdx )
					fragmentTransaction.hide(mFragments[i]);
			}
			fragmentTransaction.commit();
		}
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		
		//	after onStop, to do restoration work
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		//	verify system resource/feature availability
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		//	Get system resource if necessary, and start all action
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		//	Stop interactive action and release system resource (e.g. GPS)
		//	Before Honeycomb, save information here rather than onSaveInstanceState, onStop.
		//	  Refer to http://developer.android.com/reference/android/app/Activity.html
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) {	
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		//	Release almost all resources
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		//	stop any background thread
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by hiding current fragment and show target fragment
		//	instead of replace fragment in sample code
		if ( mFragments != null ) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.hide(mFragments[mCurFragIdx]);
			mCurFragIdx = position;
			fragmentTransaction.show(mFragments[mCurFragIdx]);
			fragmentTransaction.commit();
			mTitle = mSectionTitles[mCurFragIdx]; 
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		private static final String ARG_SECTION_TITLE = "section_title";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber, String sectionTitle) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			args.putString(ARG_SECTION_TITLE, sectionTitle);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER) + 1) + " - " + getArguments().getString(ARG_SECTION_TITLE));
			return rootView;
		}
	}

}
