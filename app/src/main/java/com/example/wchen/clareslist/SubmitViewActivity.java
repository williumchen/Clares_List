package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by wchen on 10/5/15.
 */
public class SubmitViewActivity extends Activity {

    ImageView postImageView;
    byte[] noImg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_view);
        // Grab intent on click of floating action button
        Intent intent = getIntent();
        final String category = intent.getStringExtra("category");
        // Initialize parse db, and grab item / desc from view
        final ParseWrapper parse = new ParseWrapper();
        final EditText newItem = (EditText) findViewById(R.id.submit_item);
        final EditText newDesc = (EditText) findViewById(R.id.submit_description);
        final EditText newContact = (EditText) findViewById(R.id.submit_contact);
        // Change edittext to drop down menu later
        final Spinner newCategory = (Spinner) findViewById(R.id.submit_category);

        // this should be changed to an enumeration in the constants class
        if (category.equals("Furniture")) newCategory.setSelection(0);
        if (category.equals("Appliances")) newCategory.setSelection(1);
        if (category.equals("Books")) newCategory.setSelection(2);
        if (category.equals("Electronics")) newCategory.setSelection(3);
        if (category.equals("Clothes")) newCategory.setSelection(4);
        if (category.equals("Shoes")) newCategory.setSelection(5);
        if (category.equals("Tickets")) newCategory.setSelection(6);
        if (category.equals("Misc")) newCategory.setSelection(7);


        final Button uploadBtn = (Button) findViewById(R.id.upload_button);
        postImageView = (ImageView) findViewById(R.id.image);
        Bitmap bm = ((BitmapDrawable)postImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1 - 100
        bm.compress(Bitmap.CompressFormat.WEBP, 1, stream);
        noImg = stream.toByteArray();

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Post Image"), 1);
            }
        });



        final Button submitBtn = (Button) findViewById(R.id.submit_button);
        // Submit button pushes posts to db

        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Convert edittext to strings
                String itemString = newItem.getText().toString();
                String descString = newDesc.getText().toString();
                String categoryString = newCategory.getSelectedItem().toString();
                Bitmap bitmap = ((BitmapDrawable)postImageView.getDrawable()).getBitmap();
                String contactString = newContact.getText().toString();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Compress image to lower quality scale 1 - 100
                bitmap.compress(Bitmap.CompressFormat.WEBP, 1, stream);
                byte[] image = stream.toByteArray();
                int length = image.length;
                Log.d("size of image", String.valueOf(length));
                if (image.length >= 128000)
                {
                    image = noImg;
                    // Code from:
                    // http://developer.android.com/guide/topics/ui/notifiers/toasts.html
                    Context context = getApplicationContext();
                    CharSequence text = "Image too big. Please choose another!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

                // Construct new post
                Posts newPost = new Posts(itemString, descString, categoryString, image, contactString);
                // Push post to db
                parse.pushPost(newPost);
                finish();
            }
        });
    }

    public void onActivityResult(int reqCode, int resCode, Intent data)
    {
        if (reqCode == 1 && resCode == RESULT_OK && data != null)
        {
            postImageView.setImageURI(data.getData());
        }
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
