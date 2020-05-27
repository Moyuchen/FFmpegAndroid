package com.moyuchen.ffmpegandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.TextView;

import com.moyuchen.ffmpegandroid.FFmpeg.FFmpeyPlayer;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }


    SurfaceView mSurfaceView;
    FFmpeyPlayer mFFmpeyPlayer;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSurfaceView = findViewById(R.id.main_surface_view);
        int i = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (i != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }

        mFFmpeyPlayer = new FFmpeyPlayer();
        mFFmpeyPlayer.setSurfaceView(mSurfaceView);
        findViewById(R.id.main_btn_play).setOnClickListener(l -> {
            play("Movies/test.mp4");
        });

        // Example of a call to a native method
//        TextView tv = findViewById(R.id.sample_text);
//        String string = stringFromJNI();
//        Log.i(TAG, "onCreate: string:"+string);
//        tv.setText(string);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    private void play(String videoName) {

        File file = new File(Environment.getExternalStorageDirectory(), videoName);
        String absolutePath = file.getAbsolutePath();
        if (file.exists()) {
            mFFmpeyPlayer.start(file);
        } else {
            Log.i(TAG, "play: 当前视频文件不存在");
        }


    }
}
