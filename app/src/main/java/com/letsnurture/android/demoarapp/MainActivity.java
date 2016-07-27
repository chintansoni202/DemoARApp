package com.letsnurture.android.demoarapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String ARG_CATEGORY_SUBCATEGORY = "CategorySubCategory";
    private String mCategorySubCategory;
    private ArchitectView architectView;

    public static Intent getActivityIntent(Context context, String categorySubCategory) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(ARG_CATEGORY_SUBCATEGORY, categorySubCategory);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIntentData();

        architectView = (ArchitectView) findViewById(R.id.architectView);

        // Replace with your key
        StartupConfiguration config = new StartupConfiguration("YOUR_KEY");
        architectView.onCreate(config);
    }

    private void getIntentData() {
        mCategorySubCategory = getIntent().getStringExtra(ARG_CATEGORY_SUBCATEGORY);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        architectView.onPostCreate();
        try {
            architectView.load("file:///android_asset/" + mCategorySubCategory + "/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onResume() {
        super.onResume();
        architectView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        architectView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        architectView.onDestroy();
    }
}
