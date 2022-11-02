package ru.voramok.slotmachineanimated;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final Operation operation = new Operation();
    private final Spin spin = new Spin();

    private int spinProgress;

    SharedPreferences sPref;
    private final String SAVED = "saved";

    private Button spinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ImageView fonUp = findViewById(R.id.imageViewFon);

        fonUp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        fonUp.setScaleType(ImageView.ScaleType.FIT_XY);

        operation.line1Result = findViewById(R.id.line1TV);
        operation.line2Result = findViewById(R.id.line2TV);
        operation.line3Result = findViewById(R.id.line3TV);

        spin.getSlotsColumn1()[0] = findViewById(R.id.imageView1_1);
        spin.getSlotsColumn1()[1] = findViewById(R.id.imageView2_1);
        spin.getSlotsColumn1()[2] = findViewById(R.id.imageView3_1);
        spin.getSlotsColumn1()[3] = findViewById(R.id.imageView4_1);

        spin.getSlotsColumn2()[0] = findViewById(R.id.imageView1_2);
        spin.getSlotsColumn2()[1] = findViewById(R.id.imageView2_2);
        spin.getSlotsColumn2()[2] = findViewById(R.id.imageView3_2);
        spin.getSlotsColumn2()[3] = findViewById(R.id.imageView4_2);

        spin.getSlotsColumn3()[0] = findViewById(R.id.imageView1_3);
        spin.getSlotsColumn3()[1] = findViewById(R.id.imageView2_3);
        spin.getSlotsColumn3()[2] = findViewById(R.id.imageView3_3);
        spin.getSlotsColumn3()[3] = findViewById(R.id.imageView4_3);

        spin.getSlotsColumn4()[0] = findViewById(R.id.imageView1_4);
        spin.getSlotsColumn4()[1] = findViewById(R.id.imageView2_4);
        spin.getSlotsColumn4()[2] = findViewById(R.id.imageView3_4);
        spin.getSlotsColumn4()[3] = findViewById(R.id.imageView4_4);

        spin.getSlotsColumn5()[0] = findViewById(R.id.imageView1_5);
        spin.getSlotsColumn5()[1] = findViewById(R.id.imageView2_5);
        spin.getSlotsColumn5()[2] = findViewById(R.id.imageView3_5);
        spin.getSlotsColumn5()[3] = findViewById(R.id.imageView4_5);

        operation.getSlotLine1()[0] = spin.getSlotsColumn1()[0];
        operation.getSlotLine1()[1] = spin.getSlotsColumn2()[0];
        operation.getSlotLine1()[2] = spin.getSlotsColumn3()[0];
        operation.getSlotLine1()[3] = spin.getSlotsColumn4()[0];
        operation.getSlotLine1()[4] = spin.getSlotsColumn5()[0];

        operation.getSlotLine2()[0] = spin.getSlotsColumn1()[1];
        operation.getSlotLine2()[1] = spin.getSlotsColumn2()[1];
        operation.getSlotLine2()[2] = spin.getSlotsColumn3()[1];
        operation.getSlotLine2()[3] = spin.getSlotsColumn4()[1];
        operation.getSlotLine2()[4] = spin.getSlotsColumn5()[1];

        operation.getSlotLine3()[0] = spin.getSlotsColumn1()[2];
        operation.getSlotLine3()[1] = spin.getSlotsColumn2()[2];
        operation.getSlotLine3()[2] = spin.getSlotsColumn3()[2];
        operation.getSlotLine3()[3] = spin.getSlotsColumn4()[2];
        operation.getSlotLine3()[4] = spin.getSlotsColumn5()[2];

        operation.coinCountView = findViewById(R.id.coinTextView);
        operation.betCountView = findViewById(R.id.betTextView);

        load();

        spinButton = findViewById(R.id.spin);
        Button resetButton = findViewById(R.id.reset);
        Button upBetButton = findViewById(R.id.upBet);
        Button lowBetButton = findViewById(R.id.lowBet);

        upBetButton.setOnClickListener(view -> {
            MyAnimations.buttonAnimation(upBetButton, 200);
            operation.upBetCount();
        });

        lowBetButton.setOnClickListener(view -> {
            MyAnimations.buttonAnimation(lowBetButton, 200);
            operation.lowBetCount();
        });

        resetButton.setOnClickListener(view -> {
            MyAnimations.buttonAnimation(resetButton, 200);
            operation.reset();
            spin.fullSlotsStartSetImage();
        });

        spinButton.setOnClickListener(view -> {
            if (Double.parseDouble(operation.betCountView.getText().toString()) < Double.parseDouble(operation.coinCountView.getText().toString()) / 3) {
                MyAnimations.buttonAnimation(spinButton, 200);
                new Thread(myThread).start();
            }

            else if (Double.parseDouble(operation.coinCountView.getText().toString()) < 3) {
                MakeToast();
            } else {
                Double tempDouble = Math.floor(Double.parseDouble(operation.coinCountView.getText().toString()) / 3);
                if(tempDouble < 1) {
                    operation.betCountView.setText("1");
                    MakeToast();
                } else {
                    operation.betCountView.setText(operation.getDecimalFormat().format(tempDouble));
                    MakeToast();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    private final Runnable myThread = new Runnable() {
        @Override
        public void run() {
            setTextSpinningHandler.sendMessage(setTextSpinningHandler.obtainMessage());
            while (spinProgress < 15) {
                if (spinProgress < 10) {
                    try {
                        fullSlotsAnimationHandler.sendMessage(fullSlotsAnimationHandler.obtainMessage());
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    try {
                        fullSlotsAnimationHandler2.sendMessage(fullSlotsAnimationHandler2.obtainMessage());
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            spinProgress = 0;
            columnRandomEndHandler.sendMessage(columnRandomEndHandler.obtainMessage());
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setTextSpinHandler.sendMessage(setTextSpinHandler.obtainMessage());
            save();
        }

        final Handler fullSlotsAnimationHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                spinProgress++;
                spin.fullSlotsAnimation(100);
            }
        };

        final Handler fullSlotsAnimationHandler2 = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                spinProgress++;
                spin.fullSlotsAnimation(150);
            }
        };

        final Handler columnRandomEndHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                spin.randomColumnAnimation(200);
            }
        };

        final Handler setTextSpinningHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                spinButton.setText(R.string.spinning);
                spinButton.setClickable(false);
            }
        };

        final Handler setTextSpinHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                spinButton.setText(R.string.spin);
                spinButton.setClickable(true);
                operation.operation();
                save();
            }
        };
    };

    void save() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED,operation.coinCountView.getText().toString());
        ed.apply();
    }

    void load() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED, "");
        if (savedText.equals("")) {
            operation.coinCountView.setText(R.string.startCoin);
        } else {
            operation.coinCountView.setText(savedText);
        }
    }

    public void MakeToast() {
        Toast.makeText(getApplicationContext(),
                "ставка не может превышать  " + "\n" +
                        "количество текущих " + "\n" +
                        "монет деленное на 3 ",
                Toast.LENGTH_SHORT).show();
    }
}