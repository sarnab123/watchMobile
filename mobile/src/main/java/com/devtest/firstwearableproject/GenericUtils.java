package com.devtest.firstwearableproject;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by global on 4/12/15.
 */
public class GenericUtils {

    /**
     * Helper Function to Load json From Assets Folder
     */
    public static String loadJSONFromAsset(Context c,
                                           String fileName) {
        String json = null;
        try {
            InputStream is = c.getAssets().open(fileName + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
