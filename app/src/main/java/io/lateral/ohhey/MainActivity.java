package io.lateral.ohhey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    @ViewById(R.id.card_view_twitter)
    CardView twitterCard;

    @ViewById(R.id.card_view_github)
    CardView gitHubCard;

    @ViewById(R.id.twitterHandle)
    TextView twitterHandle;

    @ViewById(R.id.gitHubHandle)
    TextView gitHubHandle;

    @AfterViews
    void afterViews() {
        startService(new Intent(this, BackgroundService_.class));

        final String twitter = PreferenceHelper.getInstance(this).getTwitter();
        String github = PreferenceHelper.getInstance(this).getGitHub();

        if (twitter != null) {
            twitterHandle.setText(twitter);
        }

        if (github != null) {
            gitHubHandle.setText(github);
        }

        final View twitterAlertView = LayoutInflater.from(this).inflate(R.layout.enter_name, null);

        final EditText twitterHandleEdit = (EditText) twitterAlertView.findViewById(R.id.editTextDialogUserInput);

        final AlertDialog twitterSetDialog = new AlertDialog.Builder(this)
                .setView(twitterAlertView)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PreferenceHelper.getInstance(MainActivity.this).setTwitter(twitterHandleEdit.getText().toString());
                        twitterHandle.setText(PreferenceHelper.getInstance(MainActivity.this).getTwitter());
                    }
                })
                .create();

        twitterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferenceHelper.getInstance(MainActivity.this).getTwitter() != null) {
                    twitterHandleEdit.setText(PreferenceHelper.getInstance(MainActivity.this).getTwitter());
                }
                twitterSetDialog.show();
            }
        });

        final View githubAlertView = LayoutInflater.from(this).inflate(R.layout.enter_name, null);

        final EditText githubHandleEdit = (EditText) githubAlertView.findViewById(R.id.editTextDialogUserInput);

        final AlertDialog githubSetDialog = new AlertDialog.Builder(this)
                .setView(githubAlertView)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PreferenceHelper.getInstance(MainActivity.this).setGitHub(githubHandleEdit.getText().toString());
                        gitHubHandle.setText(PreferenceHelper.getInstance(MainActivity.this).getGitHub());
                    }
                })
                .create();

        gitHubCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferenceHelper.getInstance(MainActivity.this).getGitHub() != null) {
                    githubHandleEdit.setText(PreferenceHelper.getInstance(MainActivity.this).getGitHub());
                }
                githubSetDialog.show();
            }
        });
    }

}
