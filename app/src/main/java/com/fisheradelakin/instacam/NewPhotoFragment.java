package com.fisheradelakin.instacam;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPhotoFragment extends ContractFragment<NewPhotoFragment.Contract> {

    private Photo mPhoto;
    private ImageView mPreview;

    public NewPhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_photo, container, false);
        setRetainInstance(true);

        final EditText caption = (EditText) v.findViewById(R.id.new_photo_caption);

        mPreview = (ImageView)  v.findViewById(R.id.photo_preview);
        Button saveButton = (Button) v.findViewById(R.id.save_new_photo);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoto.setCaption(caption.getText().toString());
                mPhoto.setUser(User.getCurrentUser());
                getContract().finishedPhoto(mPhoto);
            }
        });

        if (mPhoto == null) {
            getContract().launchCamera();
        } else {
            loadThumbnail(mPhoto);
        }
        return v;
    }

    public void updatePhoto(Photo photo) {
        mPhoto = photo;
        loadThumbnail(mPhoto);
    }

    private void loadThumbnail(Photo photo) {
        Picasso.with(getActivity()).load(photo.getFile()).into(mPreview);
    }

    public interface Contract {
        void launchCamera();
        void finishedPhoto(Photo photo);
    }

}
