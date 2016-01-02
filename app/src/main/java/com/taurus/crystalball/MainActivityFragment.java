package com.taurus.crystalball;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private CrystalBall mCrystallBall = new CrystalBall();
    private TextView mAnswerTextView;
    private ImageView mCrystallBallImage;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

        //Assign the Views from the layout file
        mAnswerTextView = (TextView)rootView.findViewById(R.id.textView1);
        mCrystallBallImage = (ImageView) rootView.findViewById(R.id.imageView1);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                handleNewAnswer();
            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector,mAccelerometer,SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mShakeDetector);
    }

    private void handleNewAnswer() {
        String answer = mCrystallBall.getAnAnswer();
        //Update the data with our dynamic answer
        mAnswerTextView.setText(answer);

        animateCrystalBall();
        animateAnswer();
        playsound();
    }

    private void playsound() {
        MediaPlayer player = MediaPlayer.create(getContext(),R.raw.crystal_ball);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

    private void animateCrystalBall(){

        mCrystallBallImage.setImageResource(R.drawable.ball_animation);

        AnimationDrawable ballAnimation = (AnimationDrawable) mCrystallBallImage.getDrawable();
       if( ballAnimation.isRunning()){
           ballAnimation.stop();
       }
        ballAnimation.start();


    }

    private void animateAnswer(){
        AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
        fadeInAnimation.setDuration(1500);
        fadeInAnimation.setFillAfter(true);

        mAnswerTextView.setAnimation(fadeInAnimation);
    }
}
