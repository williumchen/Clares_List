package com.example.wchen.clareslist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by wchen on 10/5/15.
 */
public class SubmitViewActivity extends Activity {

    ResizableImageView postImageView;

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
        // Change edittext to drop down menu later
        final Spinner newCategory = (Spinner) findViewById(R.id.submit_category);

        if (category.equals("Furniture")) newCategory.setSelection(0);
        if (category.equals("Appliances")) newCategory.setSelection(1);
        if (category.equals("Books")) newCategory.setSelection(2);
        if (category.equals("Electronics")) newCategory.setSelection(3);
        if (category.equals("Clothes")) newCategory.setSelection(4);
        if (category.equals("Shoes")) newCategory.setSelection(5);
        if (category.equals("Tickets")) newCategory.setSelection(6);
        if (category.equals("Misc")) newCategory.setSelection(7);


        final Button uploadBtn = (Button) findViewById(R.id.upload_button);

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
                //String imageString =

                // Construct new post using item and desc
                // Add more to this (maybe image?)
                Posts newPost = new Posts(itemString, descString, categoryString);
                // Push post to db
                parse.pushPost(newPost);
                finish();
            }
        });
    }

    public void onActivityResult(int reqCode, int resCode, Intent data)
    {
        ParseWrapper pw = new ParseWrapper();
        if (resCode == RESULT_OK)
        {
            if (reqCode == 1)
            {
                // the address of the image
                Uri imageUri = data.getData();

                InputStream inputStream;

                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    // Convert image to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    // Compress image to lower quality scale 1 - 100
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] image = stream.toByteArray();

                    pw.imageUpload(image);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }
            }
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
