package com.android.chameleoncards;

import androidx.annotation.Nullable;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

import com.android.chameleoncards.databinding.ActivityYourCardsBinding;

import java.util.ArrayList;

public class YourCardsActivity extends MenuActivity {

    ActivityYourCardsBinding activityYourCardsBinding;

    GridView gridView;
    ArrayList<Card> list;
    CardListAdapter adapter = null;
    DBHelper DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityYourCardsBinding = ActivityYourCardsBinding.inflate(getLayoutInflater());
        setContentView(activityYourCardsBinding.getRoot());
        allocateActivityTitle("Your Cards");

        DB = new DBHelper(this);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new CardListAdapter(this, R.layout.card_items, list);
        gridView.setAdapter(adapter);

        String[] selectionArgs = {preferenceManager.getString("user_logged_in")};
        Cursor cursor = DB.getImageData("SELECT * FROM CARDS WHERE USERNAME = ?", selectionArgs);
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            byte[] image = cursor.getBlob(2);

            list.add(new Card(id, username, image));
        }

        adapter.notifyDataSetChanged();

    }
}