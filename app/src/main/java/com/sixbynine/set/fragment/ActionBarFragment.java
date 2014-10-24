package com.sixbynine.set.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by steviekideckel on 10/23/14.
 */
public class ActionBarFragment extends Fragment{

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ActionBarActivity == false){
            throw new IllegalArgumentException("Parent Activity must be an ActionBarActivity or descendant");
        }
    }

    public ActionBarActivity getActionBarActivity(){
        return (ActionBarActivity) getActivity();
    }
}
