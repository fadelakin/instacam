package com.fisheradelakin.instacam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Fisher on 6/23/15.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private List<Photo> mPhotos;
    private Context mContext;

    public FeedAdapter(Context context, List<Photo> photos) {
        mContext = context;
        mPhotos = photos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Photo photo = mPhotos.get(i);
        User user = photo.getUser();
        Picasso.with(mContext).load(photo.getFile()).into(viewHolder.mPhoto);
        viewHolder.mCaption.setText(photo.getCaption());
        viewHolder.mUsername.setText(user.getFirstName() + " " + user.getLastName());
        Picasso.with(mContext).load(user.getAvatarURL()).into(viewHolder.mAvatar);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mAvatar;
        private final ImageView mPhoto;
        private final TextView mCaption;
        private final TextView mUsername;

        public ViewHolder(View itemView) {
            super(itemView);
            mAvatar = (ImageView) itemView.findViewById(R.id.feed_item_user_avatar);
            mPhoto = (ImageView) itemView.findViewById(R.id.feed_item_photo);
            mCaption = (TextView) itemView.findViewById(R.id.feed_item_caption);
            mUsername = (TextView) itemView.findViewById(R.id.feed_item_user_name);
        }
    }
}
