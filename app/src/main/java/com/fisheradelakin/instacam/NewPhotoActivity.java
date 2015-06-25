package com.fisheradelakin.instacam;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class NewPhotoActivity extends AppCompatActivity implements NewPhotoFragment.Contract {

    private static final int CAMERA_REQUEST = 10;
    public static final String PHOTO_EXTRA = "PHOTO_EXTRA";

    private static final String PHOTO_STATE_EXTRA = "PHOTO";

    private Photo mPhoto;
    private NewPhotoFragment mNewPhotoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);

        mNewPhotoFragment = (NewPhotoFragment) getFragmentManager().findFragmentById(R.id.new_photo_frag_container);
        if(mNewPhotoFragment == null) {
            mNewPhotoFragment = new NewPhotoFragment();

            getFragmentManager().beginTransaction()
                    .add(R.id.new_photo_frag_container, mNewPhotoFragment)
                    .commit();
        }

    }

    public void launchCamera() {
        Intent i  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        mPhoto = new Photo();

        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhoto.getFile()));

        startActivityForResult(i, CAMERA_REQUEST);
    }

    @Override
    public void finishedPhoto(Photo photo) {
        Intent i = new Intent();
        i.putExtra(PHOTO_EXTRA, photo);
        setResult(RESULT_OK, i);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            if(mPhoto.getFile() != null) {
                mNewPhotoFragment.updatePhoto(mPhoto);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_photo, menu);
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
