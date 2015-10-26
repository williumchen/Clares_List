package com.example.wchen.clareslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wchen on 10/26/15.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    // ViewHolder implements onclick listener for each card
    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView categoryTextView;
        protected Button subButton;
        private Context context;

        // Constructor takes in context and the itemView
        public ViewHolder(Context context, View itemView) {
            super(itemView);

            // Fetch the item and description view objects in the recycler view
            categoryTextView = (TextView) itemView.findViewById(R.id.category);
            this.context = context;
        }
    }

    // Store a member variable for the contacts
    public ArrayList<String> mCategory;

    public static ArrayList<String> getCategory() {
        ArrayList<String> category = new ArrayList<>(Arrays.asList(Constants.CATEGORIES));
        return category;
    }

    // Pass in the contact array into the constructor
    public CategoryAdapter(ArrayList<String> category) {
        mCategory = category;
    }

    @Override
    // On create, inflate the layout for post viewing
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View categoryView = inflater.inflate(R.layout.activity_category, parent, false);
        ViewHolder view = new ViewHolder(context, categoryView);
        return view;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder viewHolder, int position) {
        Button button = viewHolder.subButton;
    }

    @Override
    public int getItemCount() {
        return mCategory.size();
    }
}
