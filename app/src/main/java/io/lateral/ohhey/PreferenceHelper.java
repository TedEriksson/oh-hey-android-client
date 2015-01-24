package io.lateral.ohhey;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

/**
 * Created by Ted Eriksson on 24/01/15.
 */
public class PreferenceHelper {
    private static final String TAG = PreferenceHelper.class.getSimpleName();
    private static PreferenceHelper instance = null;
    Context context;
    SharedPreferences preferences;
    private static final String NAME = "ohhey_prefs";

    private static final class Prefs {
        private static final String USER_ID = "USER_ID";
        private static final String TWITTER = "TWITTER";
        private static final String GITHUB = "GITHUB";
    }

    private PreferenceHelper(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NAME, 0);
    }

    public static PreferenceHelper getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceHelper(context);
        }
        return instance;
    }

    public String getUserId() {
        String id = preferences.getString(Prefs.USER_ID, null);
        if (id == null) {
            id = UUID.randomUUID().toString();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Prefs.USER_ID, id);
            editor.commit();
        }
        return id;
    }

    public String getTwitter() {
        return preferences.getString(Prefs.TWITTER, null);
    }

    public String getGitHub() {
        return preferences.getString(Prefs.GITHUB, null);
    }

    public void setTwitter(String handle) {
        preferences.edit().putString(Prefs.TWITTER, handle).commit();
    }

    public void setGitHub(String handle) {
        preferences.edit().putString(Prefs.GITHUB, handle).commit();
    }
}
