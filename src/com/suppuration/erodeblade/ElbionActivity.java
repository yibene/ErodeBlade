package com.suppuration.erodeblade;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;

import com.suppuration.erodeblade.armory.ArmoryFragment;
import com.suppuration.erodeblade.map.MapFragment;
import com.suppuration.erodeblade.views.FragmentTabHost;

public class ElbionActivity extends GlazeQuadrantActivity implements GlazeQuadrantListener {

    private static final String TAG = ElbionActivity.class.getSimpleName();

    private FragmentManager mFM;
    private InputMethodManager mIMM;
    private ConnectivityManager mCM;

    private ActionBar mActionBar;
    private ProgressDialog mLoadingDialog;

    private FrameLayout mTabContent;
    private FragmentTabHost mTabHost;
    private TabWidget mTabWidget;
    private ImageView mTabBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFM = getFragmentManager();
        mIMM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mCM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        setContentView(R.layout.activity_sacrilege);

        TextView textViewColorful = (TextView) findViewById(R.id.textViewColorful);
        int statusH = getStatusBarHeight();
        int actionBarH = getActionBarHeight();
        textViewColorful.setHeight(statusH + actionBarH);
        textViewColorful.setBackgroundColor(getResources().getColor(R.color.main_color));

        mTabContent = (FrameLayout) findViewById(R.id.container);
        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        //if any specific tab change needed, here you are
        /*TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
            }
        };
        mTabHost.setOnTabChangedListener(tabChangeListener);*/
        mTabWidget = (TabWidget) findViewById(android.R.id.tabs);
        mTabHost.setup(this, getFragmentManager(), R.id.container);

        View mapTab = LayoutInflater.from(this).inflate(R.layout.tab_host_item, null);
        ((ImageView) mapTab.findViewById(R.id.icon)).setImageResource(R.drawable.map_icon);
        View armoryTab = LayoutInflater.from(this).inflate(R.layout.tab_host_item, null);
        ((ImageView) armoryTab.findViewById(R.id.icon)).setImageResource(R.drawable.armory_icon_a);
        View tempTab = LayoutInflater.from(this).inflate(R.layout.tab_host_item, null);
        ((ImageView) tempTab.findViewById(R.id.icon)).setImageResource(R.drawable.under_construction_icon);
        // Add each tab
        mTabHost.addTab(mTabHost.newTabSpec(ConstantValues.TAG_MAP)
                                .setIndicator(mapTab),
                        MapFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(ConstantValues.TAG_ARMORY)
                                .setIndicator(armoryTab),
                        ArmoryFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(ConstantValues.TAG_ARMORY)
                                .setIndicator(tempTab),
                        ArmoryFragment.class, null);

        mTabHost.setCurrentTabByTag(ConstantValues.TAG_MAP);
        //use tabhost.postDelayed to do animation
        /*mTabHost.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 3000);*/

        mActionBar = getActionBar();
        if (mActionBar == null) {
            Log.w(TAG, "can't get action bar");
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private int getStatusBarHeight() {
        final Display display = getWindowManager().getDefaultDisplay();
        if (display != null && display.getDisplayId() != Display.DEFAULT_DISPLAY) {
            return 0;
        }
        int h = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            h = getResources().getDimensionPixelSize(resourceId);
        }
        return h;
    }

    private int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        } else {
            return 0;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
