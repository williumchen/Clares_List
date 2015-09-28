package com.example.wchen.clareslist;

import android.content.Context;
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
        private Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.item);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
            this.context = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
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

//public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
//
//    private List<Posts> postList;
//
//    public PostAdapter(List<Posts> postList) {
//        this.postList = postList;
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return postList.size();
//    }
//
//    @Override
//    public void onBindViewHolder(PostViewHolder postViewHolder, int i) {
//        Posts post = postList.get(i);
////        postViewHolder.vName.setText(post.name);
//        postViewHolder.vDescription.setText(post.description);
////        postViewHolder.vContact.setText(post.contact);
//        postViewHolder.vTitle.setText(post.item);
//    }
//
//    @Override
//    public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.
//                from(viewGroup.getContext()).
//                inflate(R.layout.card_layout, viewGroup, false);
//
//        return new PostViewHolder(itemView);
//    }
//
//    public static class PostViewHolder extends RecyclerView.ViewHolder {
//
//        //        protected TextView vName;
//        protected TextView vDescription;
//        //        protected TextView vContact;
//        protected TextView vTitle;
//
//        public PostViewHolder(View v) {
//            super(v);
////            vName =  (TextView) v.findViewById(R.id.txtName);
//            vDescription = (TextView)  v.findViewById(R.id.txtDescription);
////            vContact = (TextView)  v.findViewById(R.id.txtContact);
//            vTitle = (TextView) v.findViewById(R.id.title);
//        }
//    }
//}
