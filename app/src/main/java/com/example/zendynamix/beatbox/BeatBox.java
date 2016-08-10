package com.example.zendynamix.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zendynamix on 7/4/2016.
*TEST
 */
public class BeatBox {
    public static final String TAG="BeatBox";
    public static final String SOUNDS_FOLDER="sample_sounds";
    private static final int MAX_SOUNDS=5;
    private AssetManager mAssetManager;
    private SoundPool soundPool;
    private List<Sound> mSounds= new ArrayList<>();
    public BeatBox(Context context){
        soundPool=new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        mAssetManager = context.getAssets();
        loadSounds();
    }

    public  void play(Sound sound){
        Integer soundID=sound.getSoundId();
        if(soundID==null){
            return;
        }
        soundPool.play(soundID,1.0f,1.0f,1,0,1.0f);
    }

    public  void release(){
        soundPool.release();
    }

    private void loadSounds() {

        String[] soundNames;
        try {
            soundNames = mAssetManager.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found" + soundNames.length + "sounds");
        } catch (IOException ioe) {
            Log.v(TAG, "cOULD NOT LIST ASSETS" + ioe);
            return;
        }
        mSounds = new ArrayList<Sound>();
        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.v(TAG, "clould not loade file" + filename, ioe);
            }
        }
    }

    private  void load(Sound sound)throws IOException{
        AssetFileDescriptor afd= mAssetManager.openFd(sound.getAssetPath());
        int soundID=soundPool.load(afd,1);
        sound.setSoundId(soundID);
    }
    public List<Sound> getSounds(){
        return mSounds;
    }
}
