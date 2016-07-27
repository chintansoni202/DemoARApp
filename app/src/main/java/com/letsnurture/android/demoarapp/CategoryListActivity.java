package com.letsnurture.android.demoarapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static Intent getActivityIntent(Context context) {
        return new Intent(context, CategoryListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        ListView listView = (ListView) findViewById(R.id.lv_category_list);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getCategoryList()));
        listView.setOnItemClickListener(this);
    }

    private List<String> getCategoryList() {
        AssetManager assetManager = getAssets();
        ArrayList<String> fileArrayList = new ArrayList<>();
        try {
            for (String file : Arrays.asList(assetManager.list("samples"))) {
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
        Log.i("OnItemClick", (String) adapterView.getItemAtPosition(i));
        startActivity(SubCategoryListActivity.getActivityIntent(this, (String) adapterView.getItemAtPosition(i)));
    }
}
