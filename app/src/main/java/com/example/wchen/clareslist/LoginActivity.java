package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if (TextUtils.isEmpty(email.getText().toString().trim()))
                {
                    Context context = getApplicationContext();
                    CharSequence text = "You forgot to enter your email!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    pw.maybeLogInUser(email.getText().toString(), "password");
                    pw.setCurrentUser();
                    Intent nextScreen = new Intent(v.getContext(), IntermediateActivity.class);
                    v.getContext().startActivity(nextScreen);
                    finish();
                }
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
