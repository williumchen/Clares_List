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

        final ParseWrapper pw = new ParseWrapper();

        // for now, we do nothing with the email entered in
        // the current user will be determined by the Android device for now
        final EditText email = (EditText) findViewById(R.id.submit_email);
        final Button loginBtn = (Button) findViewById(R.id.login_button);

        // takes us to the next screen
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pw.maybeLogInUser(email.getText().toString(), "password");
                pw.setCurrentUser();
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
