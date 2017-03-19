package com.cr.support;



public class PlayAudio extends Service {
    MediaPlayer mp;
    public PlayAudio() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.b); //res/raw/b.mp3
        mp.setLooping(true);
        Log.d("Mp3 Audio", "On start");
        mp.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
/*<service
            android:name=".support.BackgroundService"
            android:exported="false" />*/