package com.fisheradelakin.instacam;

import android.os.Environment;

import java.io.File;
import java.util.UUID;

/**
 * Created by Fisher on 6/23/15.
 */
public class Photo {

    private static final File sDIRECTORY = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    private UUID mID;

    Photo() {
        mID = UUID.randomUUID();
    }

    public UUID getID() {
        return mID;
    }

    public File getFile() {
        return new File(sDIRECTORY, mID.toString());
    }
}
