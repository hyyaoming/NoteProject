package project.recyclerview.lym.org.recyclerviewlibrary.util;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Desp.
 *
 * @author yaoming.li
 * @version 8.1.0
 * @since 2017-03-20 10:45
 */
public class SharedPrefUtil {

    private static SharedPrefUtil mInstance;

    private SharedPrefUtil(){

    }

    public static SharedPrefUtil getInstance(){
        if(mInstance == null){
            synchronized (SharedPrefUtil.class){
                if(mInstance == null){
                    mInstance = new SharedPrefUtil();
                }
            }
        }
        return mInstance;
    }


    public static void putString(String key,String defaultValue,Context context){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key,defaultValue).commit();
    }

    public static String getString(String key,Context context,String defaultValue){
       return PreferenceManager.getDefaultSharedPreferences(context).getString(key,defaultValue);
    }

}
