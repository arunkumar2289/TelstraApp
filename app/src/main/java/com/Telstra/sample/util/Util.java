package com.Telstra.sample.util;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.Telstra.sample.model.ImageData;
import com.Telstra.sample.model.LiveData;

import java.util.Iterator;
import java.util.List;

/**
 * This class is created for  network connection checking
 *
 * */
public class Util {

    /**
     * Checking network connection
     * If network available returns true else false
     *
     * */
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    /**
     * Remove null inputs
     * */
    public static List<ImageData> removeNull(List<ImageData> liveData) {
        Iterator itr = liveData.iterator();
        while (itr.hasNext()) {
            ImageData datas = (ImageData) itr.next();
            if (TextUtils.isEmpty(datas.description) && TextUtils.isEmpty(datas.imageHref) && TextUtils.isEmpty(datas.title)) {
                itr.remove();
            }
        }
      return  liveData;
    }

}