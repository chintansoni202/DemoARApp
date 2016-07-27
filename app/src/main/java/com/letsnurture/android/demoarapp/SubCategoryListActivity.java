package com.letsnurture.android.demoarapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubCategoryListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String ARG_CATEGORY = "Category";
    private String mCategory;

    public static Intent getActivityIntent(Context context, String category) {
        Intent intent = new Intent(context, SubCategoryListActivity.class);
        intent.putExtra(ARG_CATEGORY, category);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_list);

        getIntentData();

        ListView listView = (ListView) findViewById(R.id.lv_sub_category_list);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getSubCategoryList(mCategory)));
        listView.setOnItemClickListener(this);
    }

    private void getIntentData() {
        mCategory = getIntent().getStringExtra(ARG_CATEGORY);
    }

    private List<String> getSubCategoryList(String category) {
        AssetManager assetManager = getAssets();
        ArrayList<String> fileArrayList = new ArrayList<>();
        try {
            for (String file : Arrays.asList(assetManager.list("samples/" + category))) {
                if (!new File(file).isFile()) {
                    fileArrayList.add(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileArrayList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(MainActivity.getActivityIntent(this, "samples/" + mCategory + "/" + adapterView.getItemAtPosition(i)));
    }
}
