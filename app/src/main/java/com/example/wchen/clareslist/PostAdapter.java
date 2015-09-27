package com.example.wchen.clareslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Posts> postList;

    public PostAdapter(List<Posts> postList) {
        this.postList = postList;
    }


    @Override
    public int getItemCount() {
        return postList.size();
    }

    @Override
    public void onBindViewHolder(PostViewHolder postViewHolder, int i) {
        Posts post = postList.get(i);
//        postViewHolder.vName.setText(post.name);
        postViewHolder.vDescription.setText(post.description);
//        postViewHolder.vContact.setText(post.contact);
        postViewHolder.vTitle.setText(post.item);
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new PostViewHolder(itemView);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        //        protected TextView vName;
        protected TextView vDescription;
        //        protected TextView vContact;
        protected TextView vTitle;

        public PostViewHolder(View v) {
            super(v);
//            vName =  (TextView) v.findViewById(R.id.txtName);
            vDescription = (TextView)  v.findViewById(R.id.txtDescription);
//            vContact = (TextView)  v.findViewById(R.id.txtContact);
            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }
}
