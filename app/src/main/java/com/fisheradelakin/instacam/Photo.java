package com.fisheradelakin.instacam;

import android.os.Environment;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Fisher on 6/23/15.
 */
public class Photo implements Serializable {

    private static final File sDIRECTORY = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    private UUID mID;
    private String mCaption;
    private User mUser;

    Photo() {
        mID = UUID.randomUUID();
    }

    public UUID getID() {
        return mID;
    }

    public File getFile() {
        return new File(sDIRECTORY, mID.toString());
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
