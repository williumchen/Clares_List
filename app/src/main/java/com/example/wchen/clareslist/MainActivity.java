package com.example.wchen.clareslist;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.Parse;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "vH9SzZSDGnse8Sub1eF4ZF8L3J30YGHxkwNYBiKd", "u6WXDTEzRs2pLXnhas3Oi8BSqhpnhZMJuCT7bgY1");
        // Initialize the recycler view
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        // Connect adapter
        PostAdapter adapter = new PostAdapter(Posts.createPostsList(20));
        recList.setAdapter(adapter);
        recList.setLayoutManager(new LinearLayoutManager(this));
        // Setting up floating action button onclicklistener
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.plus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "FAB clicked!", Toast.LENGTH_SHORT).show();
            }
        });
//        recList.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        recList.setLayoutManager(llm);
//
//        PostAdapter pa = new PostAdapter(createList(30));
//        recList.setAdapter(pa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
        //testing more
    }
}


//    private List<Posts> createList(int size) {
//
//        List<Posts> result = new ArrayList<Posts>();
//        for (int i=1; i <= size; i++) {
//            Posts post = new Posts();
//            post.item = Posts.ITEM + i;
//            post.name = posts.NAME_PREFIX + i;
//            post.description = Posts.DESCRIPTION + i;
//            post.contact = posts.CONTACT + i + "@test.com";
//
//            result.add(post);
//        }
//        return result;
//    }
//}
