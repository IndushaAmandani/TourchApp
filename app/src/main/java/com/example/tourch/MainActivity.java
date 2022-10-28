package com.example.tourch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton btnFlash;
    CameraManager camera;
    String cameraID;
    ImageView background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFlash = findViewById(R.id.btnFlash);

        background = findViewById(R.id.background);

       camera = (CameraManager) getSystemService(CAMERA_SERVICE);

        try {
            cameraID = camera.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        btnFlash.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Boolean btnState = btnFlash.isChecked();

                if(btnState){
                            try {
                                camera.setTorchMode(cameraID,true);
                                background.setVisibility(View.VISIBLE);
                                btnFlash.setBackgroundColor(Color.GREEN);
                                btnFlash.setTextColor(Color.BLACK);

                                } catch (CameraAccessException e) {
                                  e.printStackTrace();
                                }
                }
                if(!btnState ) {
                    try {
                        camera.setTorchMode(cameraID,false);
                        background.setVisibility(View.INVISIBLE);
                        btnFlash.setBackgroundColor(Color.WHITE);

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public void on(View v){
//        try {
//            camera.setTorchMode(cameraID,true);
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
//
//    }
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public  void off(View view)throws CameraAccessException{
//        camera.setTorchMode(cameraID,false);
//
//    }

}