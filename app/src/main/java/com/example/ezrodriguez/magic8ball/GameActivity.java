package com.example.ezrodriguez.magic8ball;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private Animation trans;
    private TextView text;
    private List<String> phrases= new ArrayList<>();

    private SensorManager sensorManager;
    private float acelVal;              //CURRENT ACCELERATION VALUE AND GRAVITY
    private float acelLast;             //LAST ACCELERATION VALUE AND GRAVITY
    private float shake;                //ACCELERATION VALUE differ FROM GRAVITY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        phrases.add("YES");
        phrases.add("NO");
        phrases.add("MAYBE");
        phrases.add("NOT SO GOOD");
        phrases.add("CAN BE");

        frameLayout = (FrameLayout) findViewById(R.id.ball8);
        trans = AnimationUtils.loadAnimation(this,R.anim.trans);
        text = (TextView) findViewById(R.id.text_game);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            acelLast = acelVal;
            acelVal = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = acelVal - acelLast;
            shake = shake*0.9f + delta;

            if(shake > 12){
                trans.reset();
                frameLayout.startAnimation(trans);
                int i = (int) (Math.random()*5);
                text.setText(phrases.get(i));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(R.string.shake);
                    }
                },3000);
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
