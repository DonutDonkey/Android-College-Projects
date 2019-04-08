package com.example.wmii.android.memology.memology;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class GameImageView extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener {

           private Bitmap           photoImage;
    static private Drawable          backImage;
           private Integer      memoryButtonId;
    static private Integer         totalId = 0;
    static private Integer lastPressedId = 404;

           private Context          appContext;
           private Boolean           isClicked;
           private ViewGroup         viewGroup;

    public GameImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        backImage = getResources().getDrawable(R.drawable.ic_question);

        setId();
        increaseTotalId();
        setBackground(backImage);

        this.setOnClickListener(this);

        appContext = context;

        viewGroup = null;

        isClicked = false;
    }

    public void setId(){ memoryButtonId = totalId; }

    public int getButtonId() { return memoryButtonId; }

    public void increaseTotalId() { totalId++; }


    public void setPhotoImage(Bitmap bitmap) { this.photoImage = bitmap; }

    public Bitmap getPhotoImage() { return this.photoImage; }

    public void setViewGroup(ViewGroup viewGroup) { this.viewGroup = viewGroup; }

    public void setIsClicked() { this.isClicked = true; }

    public void setIsNotClicked() { this.isClicked = false; }


    @Override
    public void onClick(View v) {
        if(!isClicked) {
            if (isGameReady()) {
                setBackground(new BitmapDrawable(getResources(),photoImage));

                checkIfCompare();
            }
        }
    }

    public Boolean isGameReady() {
        if(photoImage == null) {
            return false;
        } else {
            return true;
        }
    }

    private void checkIfCompare() {
        if(lastPressedId == 404) {
            lastPressedId = this.memoryButtonId;
        } else {
            compareMemoryButtons();
        }
    }

    private void compareMemoryButtons() {
        GameImageView previousPressedButton = getPreviousButton(lastPressedId);

        if(compare(previousPressedButton)) {
            Toast.makeText(appContext , "HOORAY", Toast.LENGTH_SHORT).show();
            this.setBackground(null); previousPressedButton.setBackground(null);
            this.setIsClicked(); previousPressedButton.setIsClicked();
        } else {
            Toast.makeText(appContext , "WRONG", Toast.LENGTH_SHORT).show();
            this.setBackground(backImage); previousPressedButton.setBackground(backImage);
        }
    }

    private GameImageView getPreviousButton(int buttonId) {
        for(int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);

            if(view instanceof GameImageView) {
                GameImageView button = (GameImageView) view;

                if(button.getButtonId() == buttonId) return button;
            }
        }

        return null;
    }

    public Boolean compare(GameImageView gameImageView) {

        if(checkIfNotItself(gameImageView.getButtonId())) { lastPressedId = 404; return false; }

        if(this.getPhotoImage().equals(gameImageView.getPhotoImage())) {
            lastPressedId = 404; return true;
        } else {
            lastPressedId = 404; return false;
        }
    }

    public Boolean checkIfNotItself(int lastPressedId) {
        if(this.getButtonId() == lastPressedId) { return true; }
        else { return false; }
    }

    public void resetGameButton(){ this.photoImage = null; this.setBackground(backImage); this.setIsNotClicked();}

    public String toString() {
        return new StringBuilder()
                .append(memoryButtonId).toString();
    }

}
