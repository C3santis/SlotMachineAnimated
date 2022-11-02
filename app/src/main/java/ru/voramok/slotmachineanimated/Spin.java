package ru.voramok.slotmachineanimated;

import android.widget.ImageView;

import java.util.concurrent.ThreadLocalRandom;

public class Spin implements MyAnimations, SetImageBitmap{

    private final int[] slotImages = {R.drawable.slot_image_1, R.drawable.slot_image_2,
            R.drawable.slot_image_3, R.drawable.slot_image_4, R.drawable.slot_image_5,
            R.drawable.slot_image_6, R.drawable.slot_image_7, R.drawable.slot_image_8,
            R.drawable.slot_image_9, R.drawable.slot_image_10};

    private final ImageView[] slotsColumn1 = new ImageView[4];
    private final ImageView[] slotsColumn2 = new ImageView[4];
    private final ImageView[] slotsColumn3 = new ImageView[4];
    private final ImageView[] slotsColumn4 = new ImageView[4];
    private final ImageView[] slotsColumn5 = new ImageView[4];

    private static final int[] slotColumnId1 = new int[4];
    private static final int[] slotColumnId2 = new int[4];
    private static final int[] slotColumnId3 = new int[4];
    private static final int[] slotColumnId4 = new int[4];
    private static final int[] slotColumnId5 = new int[4];

    public void columnAnimation(ImageView[] column, int duration, int[] slotColumnId) {

        slotColumnId[3] = slotColumnId[2];
        slotColumnId[2] = slotColumnId[1];
        slotColumnId[1] = slotColumnId[0];

        SetImageBitmap.columnSetImageBitmap(column);

        //ratio 9 = 10% (std)
        //ratio -1 = 100%

        int currentIndex = getRandom(9);
        column[0].setImageResource(slotImages[currentIndex]);
        slotColumnId[0] = currentIndex;

        MyAnimations.slotAnimation(column[0], duration);
        MyAnimations.slotAnimation(column[1], duration);
        MyAnimations.slotAnimation(column[2], duration);
        MyAnimations.slotAnimation(column[3], duration);
    }

    public void fullSlotsAnimation( int Duration) {
        columnAnimation(slotsColumn1, Duration, slotColumnId1);
        columnAnimation(slotsColumn2, Duration, slotColumnId2);
        columnAnimation(slotsColumn3, Duration, slotColumnId3);
        columnAnimation(slotsColumn4, Duration, slotColumnId4);
        columnAnimation(slotsColumn5, Duration, slotColumnId5);
    }

    void columnStartSetImage(ImageView[] column) {
        column[0].setImageResource(R.drawable.slot_image_2);
        column[1].setImageResource(R.drawable.slot_image_2);
        column[2].setImageResource(R.drawable.slot_image_2);
        column[3].setImageResource(R.drawable.slot_image_2);
    }

    public void fullSlotsStartSetImage() {
        columnStartSetImage(slotsColumn1);
        columnStartSetImage(slotsColumn2);
        columnStartSetImage(slotsColumn3);
        columnStartSetImage(slotsColumn4);
        columnStartSetImage(slotsColumn5);
    }

    public void randomColumnAnimation(int Duration) {
        int currentIndex1 = (int) (Math.random() * 5);
        int currentIndex2 = (int) (Math.random() * 5);
        switch (currentIndex1) {
            case 0:
                columnAnimation(slotsColumn1, Duration, slotColumnId1);
                break;
            case 1:
                columnAnimation(slotsColumn2, Duration, slotColumnId2);
                break;
            case 2:
                columnAnimation(slotsColumn3, Duration, slotColumnId3);
                break;
            case 3:
                columnAnimation(slotsColumn4, Duration, slotColumnId4);
                break;
            case 4:
                columnAnimation(slotsColumn5, Duration, slotColumnId5);
                break;
        }

        switch (currentIndex2) {
            case 0:
                columnAnimation(slotsColumn1, Duration, slotColumnId1);
                break;
            case 1:
                columnAnimation(slotsColumn2, Duration, slotColumnId2);
                break;
            case 2:
                columnAnimation(slotsColumn3, Duration, slotColumnId3);
                break;
            case 3:
                columnAnimation(slotsColumn4, Duration, slotColumnId4);
                break;
            case 4:
                columnAnimation(slotsColumn5, Duration, slotColumnId5);
                break;
        }
    }

    public int getRandom(int ratio) {
        return ThreadLocalRandom.current().nextInt(10) > ratio ? 1 :  ThreadLocalRandom.current().nextInt(10);
    }

    public ImageView[] getSlotsColumn1() {
        return slotsColumn1;
    }

    public ImageView[] getSlotsColumn2() {
        return slotsColumn2;
    }

    public ImageView[] getSlotsColumn3() {
        return slotsColumn3;
    }

    public ImageView[] getSlotsColumn4() {
        return slotsColumn4;
    }

    public ImageView[] getSlotsColumn5() {
        return slotsColumn5;
    }

    public static int[] getSlotColumnId1() {
        return slotColumnId1;
    }

    public static int[] getSlotColumnId2() {
        return slotColumnId2;
    }

    public static int[] getSlotColumnId3() {
        return slotColumnId3;
    }

    public static int[] getSlotColumnId4() {
        return slotColumnId4;
    }

    public static int[] getSlotColumnId5() {
        return slotColumnId5;
    }
}


