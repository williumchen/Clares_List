package com.example.wchen.clareslist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class EditSubmitViewActivity extends AppCompatActivity {

    ImageView postImageView;
    // the byte array of the default image
    byte[] noImg = null;
    // the maximum number of bytes allowed to be uploaded
    int max = 128000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_submit_view);

        final ParseWrapper parse = new ParseWrapper();
        final EditText newItem = (EditText) findViewById(R.id.submit_item);
        final EditText newDesc = (EditText) findViewById(R.id.submit_description);
        final EditText newContact = (EditText) findViewById(R.id.submit_contact);

        final Spinner newCategory = (Spinner) findViewById(R.id.submit_category);
        // Make the category be Furniture, user can always select a different one
        newCategory.setSelection(0);
        // intent has the item, description, and contact texts from the users old post
        // prefill these edittext views with this info
        Intent intent = getIntent();
        newItem.setText(intent.getStringExtra("item"));
        newDesc.setText(intent.getStringExtra("description"));
        newContact.setText(intent.getStringExtra("contact"));

        // allows user to choose an image by looking through the phone's gallery
        final Button uploadBtn = (Button) findViewById(R.id.upload_button);
        postImageView = (ImageView) findViewById(R.id.image);
        // the bitmap of the default image
        Bitmap bm = ((BitmapDrawable)postImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1
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

        // Submit button pushes posts to db
        final Button submitBtn = (Button) findViewById(R.id.submit_button);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Convert edittext and selected category to strings
                String itemString = newItem.getText().toString();
                String descString = newDesc.getText().toString();
                String contactString = newContact.getText().toString();
                String categoryString = newCategory.getSelectedItem().toString();
                // get the bitmap from the imageview
                Bitmap bitmap = ((BitmapDrawable)postImageView.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Compress image to lower quality scale 1
                bitmap.compress(Bitmap.CompressFormat.WEBP, 1, stream);
                byte[] image = stream.toByteArray();
                // if the image exceeds the allowable number of bytes
                if (image.length >= max)
                {
                    // set byte array to default image
                    image = noImg;
                    // give appropriate comment to user
                    // Code from:
                    // http://developer.android.com/guide/topics/ui/notifiers/toasts.html
                    Context context = getApplicationContext();
                    CharSequence text = "Image too big. Go to My Posts and choose another!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
                // Construct new post
                Posts newPost = new Posts(itemString, descString, categoryString, image, contactString);
                // Push post to parse
                parse.pushPost(newPost);
                finish();
            }
        });
    }

    public void onActivityResult(int reqCode, int resCode, Intent data)
    {
        // if we can get the image from the image gallery
        if (reqCode == 1 && resCode == RESULT_OK && data != null)
        {
            // set the URI of the image view
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
