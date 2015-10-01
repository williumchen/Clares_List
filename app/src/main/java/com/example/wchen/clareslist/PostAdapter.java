package com.example.wchen.clareslist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView nameTextView;
        protected TextView descriptionTextView;
        protected ResizableImageView itemImage;
        private Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.item);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
            //itemImage = (ResizableImageView) itemView.findViewById(R.id.picture);
            this.context = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent nextScreen = new Intent(view.getContext(), PostViewActivity.class);
            nextScreen.putExtra("item", nameTextView.getText().toString());
            nextScreen.putExtra("description", descriptionTextView.getText().toString());
            view.getContext().startActivity(nextScreen);
            //Toast.makeText(context, nameTextView.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    private List<Posts> mPosts;

    public PostAdapter(List<Posts> posts) {
        mPosts = posts;
    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.card_layout, parent, false);
        ViewHolder view = new ViewHolder(context, postView);
        return view;
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder viewHolder, int position) {
        Posts post = mPosts.get(position);
        TextView itemView = viewHolder.nameTextView;
        itemView.setText(post.getItem());
        TextView descView = viewHolder.descriptionTextView;
        descView.setText(post.getDescription());
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}