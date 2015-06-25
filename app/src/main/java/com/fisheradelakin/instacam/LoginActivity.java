package com.fisheradelakin.instacam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private UiLifecycleHelper mUiLifecycleHelper; // facebook login stuff

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_birthday");

        mUiLifecycleHelper = new UiLifecycleHelper(this, new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState sessionState, Exception e) {
                onSessionStateChanged(session, sessionState, e);
            }
        });

        mUiLifecycleHelper.onCreate(savedInstanceState);
    }

    private void onSessionStateChanged(final Session session, SessionState sessionState, Exception e) {
        if(sessionState.isOpened()) {
            List<String> permissions = Session.getActiveSession().getPermissions();

            boolean hasBirthdayPermission = false;
            for(String permission : permissions) {
                if(permission.equals("user_birthday")) {
                    hasBirthdayPermission = true;
                }
            }

            if(!hasBirthdayPermission) {
                Session.NewPermissionsRequest permissionsRequest = new Session.NewPermissionsRequest(this, "user_birthday");
                session.requestNewReadPermissions(permissionsRequest);
                return;
            }
            Bundle parameters = new Bundle();
            parameters.putString("fields", "picture, first_name, last_name, birthday");
            Request request = new Request(session, "/me", parameters, HttpMethod.GET, new Request.Callback() {
                @Override
                public void onCompleted(Response response) {
                    if(session == Session.getActiveSession() && response.getGraphObject() != null) {
                        Log.d(TAG, response.getGraphObject().toString());
                        //Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        //startActivity(i);
                    }

                    if(response.getError() != null) {
                        Log.d(TAG, "Error is " + response.getError());
                    }
                }
            });
            Log.d(TAG, "Session opened");
            request.executeAsync();
        } else {
            Log.d(TAG, "Session closed");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mUiLifecycleHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUiLifecycleHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mUiLifecycleHelper.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mUiLifecycleHelper.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUiLifecycleHelper.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
