package com.sixbynine.set.manager;

/**
 * Created by steviekideckel on 11/2/14.
 */
public class Timer extends Manager{
    private static Timer sInstance;

    private int mTotalTime;
    private int mSecondsRemaining;
    private boolean mRunning;

    private Timer(){}

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

    public void setDuration(int seconds){
        mTotalTime = seconds;
    }

    public void start(){
        mSecondsRemaining = mTotalTime;
        mHandler.postDelayed(mTickRunnable, 1000);
        publish(UpdateEvent.TIMER_START);
    }

    public void pause(){
        mRunning = false;
        publish(UpdateEvent.TIMER_PAUSE);
    }

    public void resume(){
        mRunning = true;
        mHandler.postDelayed(mTickRunnable, 1000);
        publish(UpdateEvent.TIMER_RESUME);
    }

    public void reset(){
        mRunning = false;
        mSecondsRemaining = mTotalTime;
        publish(UpdateEvent.TIMER_RESET);
    }

    public int getTotalTime(){
        return mTotalTime;
    }

    public int getTimeRemaining(){
        return mSecondsRemaining;
    }

    private Runnable mTickRunnable = new Runnable() {
        @Override
        public void run() {
            if(mRunning){
                mSecondsRemaining--;
                if(mSecondsRemaining > 0){
                    publish(UpdateEvent.TIMER_TICK, mSecondsRemaining);
                }else{
                    publish(UpdateEvent.TIMER_FINISH);
                }
                mHandler.postDelayed(this, 1000);
            }
        }
    };





}
