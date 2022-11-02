package ru.voramok.slotmachineanimated;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public interface SetImageBitmap {

    static void columnSetImageBitmap(ImageView[] column) {

        Bitmap bm4_1 =((BitmapDrawable)column[2].getDrawable()).getBitmap();
        column[3].setImageBitmap(bm4_1);

        Bitmap bm3_1 =((BitmapDrawable)column[1].getDrawable()).getBitmap();
        column[2].setImageBitmap(bm3_1);

        Bitmap bm2_1 =((BitmapDrawable)column[0].getDrawable()).getBitmap();
        column[1].setImageBitmap(bm2_1);
    }
}
