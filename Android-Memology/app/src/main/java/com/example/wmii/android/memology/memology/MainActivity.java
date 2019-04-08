package com.example.wmii.android.memology.memology;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private int number_of_assigned_pictures = 0;
    private ArrayList<Bitmap> takenCameraPictures;
    private ViewGroup allViewElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takenCameraPictures = new ArrayList<Bitmap>(4);
    }

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, number_of_assigned_pictures);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            assignCapturedImage(imageBitmap);
            checkIfGameIsReady();
        }
    }

    private void assignCapturedImage(Bitmap imageBitmap) {
        this.takenCameraPictures.add(imageBitmap);
        this.takenCameraPictures.add(imageBitmap);

        number_of_assigned_pictures = number_of_assigned_pictures + 2;
    }

    private void checkIfGameIsReady() {
        if (number_of_assigned_pictures == 4) {
            Collections.shuffle(takenCameraPictures);
            allViewElements = (ViewGroup) findViewById(R.id.mainLayout);
            startGame(allViewElements);
            Toast.makeText(MainActivity.this, "READY", Toast.LENGTH_SHORT).show();
        }
    }

    private void startGame(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);

            if (view instanceof GameImageView) {
                ((GameImageView) view).setPhotoImage(takenCameraPictures.get(((GameImageView) view).getButtonId()));
                ((GameImageView) view).setViewGroup(viewGroup);
            }
        }
    }


    public void resetGameOnClick(View view) {
        takenCameraPictures.clear();
        number_of_assigned_pictures = 0;

        allViewElements = (ViewGroup) findViewById(R.id.mainLayout);
        resetGameButtons(allViewElements);
    }

    private void resetGameButtons(ViewGroup viewGroup) {
        for (int i = 0; i < allViewElements.getChildCount(); i++) {
            View view = allViewElements.getChildAt(i);

            if (view instanceof GameImageView) {
                ((GameImageView) view).resetGameButton();
            }
        }
    }

}

