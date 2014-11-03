package com.sixbynine.set.manager;

import android.os.Handler;
import android.os.Looper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by steviekideckel on 11/3/14.
 */
public abstract class Manager {

    private Set<UpdateListener> mListeners = new HashSet<UpdateListener>();
    protected Handler mHandler = new Handler(Looper.getMainLooper());

    public boolean subscribe(UpdateListener listener){
        return mListeners.add(listener);
    }

    public boolean unSubscribe(UpdateListener listener){
        return mListeners.remove(listener);
    }

    protected void publish(UpdateEvent e, Object... data){
        if(Looper.getMainLooper().getThread() == Thread.currentThread()){
            for(UpdateListener listener : mListeners){
                listener.update(e, data);
            }
        }else{
            mHandler.post(new UpdateRunnable(e, data));
        }

    }

    private class UpdateRunnable implements Runnable{
        private UpdateEvent mEvent;
        private Object[] mData;

        public UpdateRunnable(UpdateEvent event, Object... data){
            mEvent = event;
            mData = data;
        }

        @Override
        public void run() {
            for(UpdateListener listener : mListeners){
                listener.update(mEvent, mData);
            }
        }
    }
}
