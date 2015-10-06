package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by wchen on 10/5/15.
 */
public class SubmitViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_view);
        Intent intent = getIntent();

        final Button submitBtn = (Button) findViewById(R.id.submit_button);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        EditText newItem = (EditText) findViewById(R.id.submit_item);
        EditText newDesc = (EditText) findViewById(R.id.submit_description);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get the back button to work
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
