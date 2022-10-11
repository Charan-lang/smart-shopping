package com.example.rabiy.styleomega14;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefUtility {

    //private static final String userPref= "USER_PREF";
    //private static final String userEmail = "";

    private static SharedPreferences mSharedPref;
    public static final String EMAIL = "EMAIL";


    private SharedPrefUtility(){

    }

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }


    /*private static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(userPref, Context.MODE_PRIVATE);
    }

    public static String getUserEmail(Context context) {
        return getSharedPreferences(context).getString(userEmail , "");
    }

    public static void setUserEmail(Context context, String email) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(userEmail , email);
        editor.commit();
    }*/

}
