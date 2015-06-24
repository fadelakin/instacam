package com.fisheradelakin.instacam;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {


    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feed, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.feed_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));
        List<Photo> photos = new ArrayList<Photo>();
        photos.add(new Photo());
        photos.add(new Photo());
        photos.add(new Photo());
        photos.add(new Photo());
        recyclerView.setAdapter(new FeedAdapter(getActivity(), photos));
        return v;
    }


}
