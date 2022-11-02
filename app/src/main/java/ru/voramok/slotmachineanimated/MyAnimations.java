package ru.voramok.slotmachineanimated;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public interface MyAnimations {

    static void slotAnimation(ImageView view, int Duration) {
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(
                ObjectAnimator.ofFloat(view, "translationY", 0, 200)
                        .setDuration(Duration)
        );
        set.start();
    }

    static void buttonAnimation(Button button, int Duration) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(button, "scaleY", 1.0F, 0.9F)
                        .setDuration(Duration),
                ObjectAnimator.ofFloat(button, "scaleX", 1.0F, 0.9F)
                        .setDuration(Duration)
        );

        set.playTogether(
                ObjectAnimator.ofFloat(button, "scaleY", 0.9F, 1.1F)
                        .setDuration(Duration),
                ObjectAnimator.ofFloat(button, "scaleX", 0.9F, 1.1F)
                        .setDuration(Duration)
        );

        set.playTogether(
                ObjectAnimator.ofFloat(button, "scaleY", 1.1F, 1.0F)
                        .setDuration(Duration),
                ObjectAnimator.ofFloat(button, "scaleX", 1.1F, 1.0F)
                        .setDuration(Duration)
        );
        set.start();

    }

    static void slotCompAnimation(ImageView imageView, int Duration) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(imageView, "scaleY", 1.0F, 0.9F)
                        .setDuration(Duration),
                ObjectAnimator.ofFloat(imageView, "scaleX", 1.0F, 0.9F)
                        .setDuration(Duration)
        );

        set.playTogether(
                ObjectAnimator.ofFloat(imageView, "scaleY", 0.9F, 1.1F)
                        .setDuration(Duration),
                ObjectAnimator.ofFloat(imageView, "scaleX", 0.9F, 1.1F)
                        .setDuration(Duration)
        );

        set.playTogether(
                ObjectAnimator.ofFloat(imageView, "scaleY", 1.3F, 1.0F)
                        .setDuration(Duration),
                ObjectAnimator.ofFloat(imageView, "scaleX", 1.3F, 1.0F)
                        .setDuration(Duration)
        );
        set.start();

    }

    static void textViewLRAnimation (TextView textView, int Duration) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(textView, "scaleY", 0.0F, 3.0F)
                        .setDuration(Duration),
                ObjectAnimator.ofFloat(textView, "scaleX", 0.0F, 3.0F)
                        .setDuration(Duration)
        );

        set.playTogether(
                ObjectAnimator.ofFloat(textView, "scaleY", 3.0F, 2.0F)
                        .setDuration(Duration),
                ObjectAnimator.ofFloat(textView, "scaleX", 3.0F, 2.0F)
                        .setDuration(Duration)
        );

        set.playTogether(
                ObjectAnimator.ofFloat(textView, "scaleY", 2.0F, 0.0F)
                        .setDuration(Duration),
                ObjectAnimator.ofFloat(textView, "scaleX", 2.0F, 0.0F)
                        .setDuration(Duration)
        );
        set.start();

    }
}
