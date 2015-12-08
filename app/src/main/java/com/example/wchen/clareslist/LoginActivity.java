package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email = (EditText) findViewById(R.id.submit_email);
        final Button loginBtn = (Button) findViewById(R.id.login_button);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(), IntermediateActivity.class);
                v.getContext().startActivity(nextScreen);
                finish();
            }
        });
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
