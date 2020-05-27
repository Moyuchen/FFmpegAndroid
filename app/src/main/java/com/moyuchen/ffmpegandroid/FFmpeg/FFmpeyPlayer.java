package com.moyuchen.ffmpegandroid.FFmpeg;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;

/**
 * @Author zhangyabo
 * @Date 2020-05-26
 * @Des
 **/
public class FFmpeyPlayer implements SurfaceHolder.Callback {
    static {
        System.loadLibrary("native-lib");
    }

    private SurfaceHolder mSurfaceHolder;

    public void setSurfaceView(SurfaceView mSurfaceView){

        if (null != mSurfaceHolder){
            this.mSurfaceHolder.removeCallback(this);
        }
        this.mSurfaceHolder = mSurfaceView.getHolder();
        this.mSurfaceHolder.addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            this.mSurfaceHolder = surfaceHolder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public void start(File file) {
        player(file.getAbsolutePath(),mSurfaceHolder.getSurface());
    }

    public native void player(String filePath,Surface mSurface);
}
