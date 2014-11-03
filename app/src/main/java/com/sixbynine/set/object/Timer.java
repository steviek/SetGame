package com.sixbynine.set.object;

import android.os.Handler;
import android.os.Looper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by steviekideckel on 11/2/14.
 */
public class Timer {
    private static Timer sInstance;

    private int mTotalTime;
    private int mSecondsRemaining;
    private Handler mHandler;
    private Set<Listener> mListeners;
    private boolean mRunning;

    public interface Listener{
        public void onTimerUpdate(TimerEvent e, Object... data);
    }

    public enum TimerEvent{
        TICK,
        PAUSE,
        START,
        STOP,
        RESET,
        RESUME,
        FINISH;
    }

    private Timer(){
        mListeners = new HashSet<Listener>();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static Timer getInstance(){
        if(sInstance == null){
            synchronized (Timer.class){
                if(sInstance == null){
                    sInstance = new Timer();
                }
            }
        }
        return sInstance;
    }

    public void addListener(Listener listener){
        mListeners.add(listener);
    }

    public void removeListener(Listener listener){
        mListeners.remove(listener);
    }

    public void setDuration(int seconds){
        mTotalTime = seconds;
    }

    public void start(){
        mSecondsRemaining = mTotalTime;
        mHandler.postDelayed(mTickRunnable, 1000);
        publish(TimerEvent.START);
    }

    public void pause(){
        mRunning = false;
        publish(TimerEvent.PAUSE);
    }

    public void resume(){
        mRunning = true;
        mHandler.postDelayed(mTickRunnable, 1000);
        publish(TimerEvent.RESUME);
    }

    public void reset(){
        mRunning = false;
        mSecondsRemaining = mTotalTime;
        publish(TimerEvent.RESUME);
    }

    public int getTotalTime(){
        return mTotalTime;
    }

    public int getTimeRemaining(){
        return mSecondsRemaining;
    }

    public void publish(TimerEvent e, Object... data){
        for(Listener l : mListeners){
            l.onTimerUpdate(e, data);
        }
    }

    private Runnable mTickRunnable = new Runnable() {
        @Override
        public void run() {
            if(mRunning){
                mSecondsRemaining--;
                if(mSecondsRemaining > 0){
                    publish(TimerEvent.TICK, mSecondsRemaining);
                }else{
                    publish(TimerEvent.FINISH);
                }
                mHandler.postDelayed(this, 1000);
            }
        }
    };





}
