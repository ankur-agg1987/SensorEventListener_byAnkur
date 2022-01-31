package com.example.sensoreventlistener_byankur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

// implement the SensorEventListener to handle any value change by Type of sensor
// and override the onSensorChanged and onAccuracyChange method of SensorEventListener
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor sensor;
    private SensorManager sensorManager;
    private float newValueofSensor;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // access the Textview of UI
        textView = (TextView) findViewById(R.id.textView);

        // get the sensor manager service
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    // we can check for any new data from sensor and update UI
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("mySensorEventValue","Value: "+sensorEvent.values[0]);
        newValueofSensor = sensorEvent.values[0];
        //update the new DATA to UI TextView
        textView.setText(String.valueOf(newValueofSensor));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    // perform the registration of SensorEventListener in onStart of activity
    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_UI);
    }
    // perform the unregistring of SensorEventListener in onStop of activity
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    /*   @Override
    protected void onResume() {
        super.onResume();
        // register the listener for Sensor Type light with normal delay value
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    */

}