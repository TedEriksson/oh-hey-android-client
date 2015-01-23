package io.lateral.ohhey;

import android.content.Context;
import android.content.SharedPreferences;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.UUID;

/**
 * Created by Ted Eriksson on 23/01/15.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User {

    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";
    private static final String PREF_TWIITER = "PREF_TWITTER";
    private static final String PREF_GITHUB = "PREF_GITHUB";

    @JsonProperty("user_id")
    public synchronized String getId(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(
                PREF_UNIQUE_ID, Context.MODE_PRIVATE);
        String uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
        if (uniqueID == null) {
            uniqueID = UUID.randomUUID().toString();
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(PREF_UNIQUE_ID, uniqueID);
            editor.commit();
        }
        return uniqueID;
    }

    @JsonProperty()
    float distance;

    @JsonProperty("twitter")
    public String getTwitter(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(
                PREF_TWIITER, Context.MODE_PRIVATE);
        return sharedPrefs.getString(PREF_TWIITER, null);
    }

    public void setTwitter(String twitter, Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(
                PREF_TWIITER, Context.MODE_PRIVATE);
        sharedPrefs.edit().putString(PREF_TWIITER, twitter).commit();
    }

    @JsonProperty("github")
    public String getGitHub(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(
                PREF_GITHUB, Context.MODE_PRIVATE);
        return sharedPrefs.getString(PREF_GITHUB, null);
    }

    public void setGitHub(String gitHub, Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(
                PREF_GITHUB, Context.MODE_PRIVATE);
        sharedPrefs.edit().putString(PREF_GITHUB, gitHub).commit();
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
