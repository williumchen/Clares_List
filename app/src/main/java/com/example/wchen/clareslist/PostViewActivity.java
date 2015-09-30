package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PostViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        TextView txtItem = (TextView) findViewById(R.id.txtItem);
        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);

        Intent intent = getIntent();
        String item = intent.getStringExtra("item");
        String description = intent.getStringExtra("description");
        txtItem.setText(item);
        txtDescription.setText(description);
    }
}
