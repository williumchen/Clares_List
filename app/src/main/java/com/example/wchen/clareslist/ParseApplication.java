package com.example.wchen.clareslist;

import android.app.Application;

import com.parse.Parse;

/**
 * Initializing parse. Extends application
 * Created by wchen on 11/1/15.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "vH9SzZSDGnse8Sub1eF4ZF8L3J30YGHxkwNYBiKd", "u6WXDTEzRs2pLXnhas3Oi8BSqhpnhZMJuCT7bgY1");
    }
}
