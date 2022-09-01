package com.android.chameleoncards;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.chameleoncards.databinding.ActivityNewCardBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class NewCard extends MenuActivity {

    ActivityNewCardBinding activityNewCardBinding;

    CardView card_background;

    ImageView card;
    ImageButton saveCard;
    ImageButton search;

    TextView receiver;
    EditText editReceiver;

    TextView message;
    EditText editMessage;

    TextView sender;
    EditText editSender;

    ImageButton saveToYourCards;

    PreferenceManager preferenceManager;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("username: ", "Adm!n123");
        Log.i("password: ", "Adm!n456");

        activityNewCardBinding = ActivityNewCardBinding.inflate(getLayoutInflater());
        setContentView(activityNewCardBinding.getRoot());
        allocateActivityTitle("New Card");

        DB = new DBHelper(this);

        message = (TextView) findViewById(R.id.message);
        editMessage = (EditText) findViewById(R.id.edit_message);

        receiver = (TextView) findViewById(R.id.receiver);
        editReceiver = (EditText) findViewById(R.id.edit_receiver);

        sender = (TextView) findViewById(R.id.sender);
        editSender = (EditText) findViewById(R.id.edit_sender);

        card = (ImageView) findViewById(R.id.card);
        saveCard = (ImageButton) findViewById(R.id.save_card);
        saveToYourCards = (ImageButton) findViewById(R.id.save_card_2);

        search = (ImageButton) findViewById(R.id.search);

        card_background = (CardView) findViewById(R.id.card_background);

        preferenceManager = PreferenceManager.getInstance(this);

        ActivityCompat.requestPermissions(NewCard.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        editMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                message.setText(editMessage.getText());

            }
        });

        editReceiver.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                receiver.setText(editReceiver.getText());

            }
        });

        editSender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sender.setText(editSender.getText());

            }
        });

        saveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToPhone();
            }
        });

        saveToYourCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card_background.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(card_background.getDrawingCache());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();

                try {
                    DB.insertCard(preferenceManager.getString("user_logged_in"), b);
                    Toast.makeText(getApplicationContext(), "Card Saved!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewCard.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onCardBackgroundSelected(View view) {
        RadioGroup radioGroup = findViewById(R.id.choose_background_group);
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.background_option_1_radio:
                card.setImageResource(R.drawable.pattern_1);
                break;
            case R.id.background_option_2_radio:
                card.setImageResource(R.drawable.pattern_2);
                break;
            case R.id.background_option_3_radio:
                card.setImageResource(R.drawable.pattern_3);
                break;
        }
    }

    private void saveToPhone() {
        if (!isRooted1()) {
            card_background.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(card_background.getDrawingCache());

            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, String.format("%d.png", System.currentTimeMillis()), null);
        } else {
            Toast.makeText(NewCard.this, "Device is rooted. Cannot save card.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isRooted1() {
        String[] suPaths = {"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su",
                "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};

        for (String suPath: suPaths) {
            if (new File(suPath).exists()) {
                return true;
            }
        }
        return false;
    }

}
