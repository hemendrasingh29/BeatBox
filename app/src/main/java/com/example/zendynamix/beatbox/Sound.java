package com.example.zendynamix.beatbox;

/**
 * Created by zendynamix on 7/4/2016.
 */
public class Sound {
    public static final String TAG = "BeatBox";
    private String mAssetPath;
    private String mName;
    private Integer soundId;

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String fileName = components[components.length - 1];
        mName = fileName.replace(".wav", "");
    }

    public String getAssetPath() {
        return mAssetPath;
    }


    public String getName() {
        return mName;
    }

    public Integer getSoundId() {
        return soundId;
    }

    public void setSoundId(Integer soundId) {
        this.soundId = soundId;
    }
}
