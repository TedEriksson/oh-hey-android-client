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

    public User(String userId, String proximity, String twitter, String github, int major, int minor) {
        this.userId = userId;
        this.proximity = proximity;
        this.twitter = twitter;
        this.github = github;
        this.major = major;
        this.minor = minor;
    }

    @JsonProperty("user_id")
    String userId;

    @JsonProperty()
    String proximity;

    @JsonProperty()
    String twitter = null;

    @JsonProperty()
    String github = null;

    @JsonProperty()
    int major;

    @JsonProperty()
    int minor;
}
