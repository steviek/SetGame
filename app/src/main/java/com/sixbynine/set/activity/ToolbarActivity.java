package com.sixbynine.set.activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.sixbynine.set.R;

/**
 * Created by steviekideckel on 11/2/14.
 */
public class ToolbarActivity extends ActionBarActivity{

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
