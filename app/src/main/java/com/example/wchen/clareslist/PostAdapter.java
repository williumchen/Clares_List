package com.example.wchen.clareslist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

// Extends recycler view adapter
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    // ViewHolder implements onclick listener for each row
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView nameTextView;
        protected TextView descriptionTextView;
        protected TextView contactTextView;
        protected TextView idTextView;
        protected ResizableImageView itemImage;
        private Context context;

        // Constructor takes in context and the itemView
        public ViewHolder(Context context, View itemView) {
            super(itemView);
            // Fetch the item and description view objects in the recycler view
            nameTextView = (TextView) itemView.findViewById(R.id.item);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
            contactTextView = (TextView) itemView.findViewById(R.id.contact);
            // post id will not be shown
            idTextView = (TextView) itemView.findViewById(R.id.info);
            itemImage = (ResizableImageView) itemView.findViewById(R.id.picture);
            this.context = context;
            // Set itemView to be an onclick listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // On click, send intent containing the item and description of the card
            Intent nextScreen;
            // if we are the in the UserPostsListActivity
            if (view.getContext() instanceof UserPostsListActivity)
            {
                // the next activity should be UserPostViewActivity so that user
                // has the option to edit or delete the post
                nextScreen = new Intent(view.getContext(), UserPostViewActivity.class);
                // pass the object id in order to enable the edit or deletion
                nextScreen.putExtra("id", idTextView.getText().toString());
            }
            else{
                // otherwise, go to the regular post view
                nextScreen = new Intent(view.getContext(), PostViewActivity.class);
            }
            nextScreen.putExtra("item", nameTextView.getText().toString());
            nextScreen.putExtra("description", descriptionTextView.getText().toString());
            nextScreen.putExtra("contact", contactTextView.getText().toString());
            view.getContext().startActivity(nextScreen);
        }
    }

    private List<Posts> mPosts;
    // Constructor for post adapter, takes in a list of posts
    public PostAdapter(List<Posts> posts) {
        mPosts = posts;
    }

    @Override
    // On create, inflate the layout for post viewing
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.card_layout, parent, false);
        ViewHolder view = new ViewHolder(context, postView);
        return view;
    }

    @Override
    // Bind views with texts
    public void onBindViewHolder(PostAdapter.ViewHolder viewHolder, int position) {
        Posts post = mPosts.get(position);
        TextView itemView = viewHolder.nameTextView;
        itemView.setText(post.getItem());
        TextView descView = viewHolder.descriptionTextView;
        descView.setText(post.getDescription());
        TextView contView = viewHolder.contactTextView;
        contView.setText(post.getContact());
        // we have idView so that we may later pass the intent with the object id
        TextView idView = viewHolder.idTextView;
        idView.setText(post.getID());
        byte[] data = post.getImage();
        // if there is an image
        if (data != null) {
            // Set the bitmap of imgView, which has a cropped height
            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            ResizableImageView imgView = viewHolder.itemImage;
            imgView.setImageBitmap(bmp);
        }
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}