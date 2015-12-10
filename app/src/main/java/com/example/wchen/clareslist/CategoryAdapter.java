package com.example.wchen.clareslist;

import android.content.Context;
import android.content.Intent;
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

    // Store a member variable for the contacts
    private static ArrayList<String> mCategory;

    public static ArrayList<String> getCategory() {
        ArrayList<String> category = new ArrayList<>(Arrays.asList(Constants.CATEGORIES));
        return category;
    }

    // Pass in the category array into the constructor
    public CategoryAdapter(ArrayList<String> category) {
        mCategory = category;
    }

    // ViewHolder implements onClick listener
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView categoryTextView;
        protected Button subButton;
        private Context context;

        // Constructor takes in context and the itemView
        public ViewHolder(Context context, View itemView) {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            categoryTextView = (TextView) itemView.findViewById(R.id.category);
            subButton = (Button) itemView.findViewById(R.id.subscribe);
            this.context = context;
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            //Toast.makeText(context, categoryTextView.getText(), Toast.LENGTH_SHORT).show();
            Intent categorySelected = new Intent(view.getContext(), MainActivity.class);
            categorySelected.putExtra("category", categoryTextView.getText().toString());
            view.getContext().startActivity(categorySelected);
        }
    }

    @Override
    // On create, inflate the layout for category viewing
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View categoryView = inflater.inflate(R.layout.activity_category, parent, false);
        ViewHolder view = new ViewHolder(context, categoryView);
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final ParseWrapper parse = new ParseWrapper();
        final String category = mCategory.get(position);
        final TextView itemView = viewHolder.categoryTextView;
        itemView.setText(category);
        final Button button = viewHolder.subButton;
        if (parse.checkSubscription(category)) {
            button.setText("Unsubscribe");
            button.setEnabled(true);
        }
        else {
            button.setText("Subscribe");
            button.setEnabled(true);
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String sub = button.getText().toString();
                if (sub == "Subscribe") {
                    parse.subscribeUser(category, true);
                } else {
                    parse.subscribeUser(category, false);
                }
                if (parse.checkSubscription(category)) {
                    button.setText("Unsubscribe");
                    button.setEnabled(true);
                }
                else {
                    button.setText("Subscribe");
                    button.setEnabled(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategory.size();
    }
}
