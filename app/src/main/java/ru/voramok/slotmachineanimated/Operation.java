package ru.voramok.slotmachineanimated;

import static ru.voramok.slotmachineanimated.MyAnimations.slotCompAnimation;
import static ru.voramok.slotmachineanimated.MyAnimations.textViewLRAnimation;

import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class Operation {

    public TextView coinCountView;

    public TextView betCountView;

    public TextView line1Result;

    public TextView line2Result;

    public TextView line3Result;


    private final ImageView[] slotLine1 = new ImageView[5];
    private final ImageView[] slotLine2 = new ImageView[5];
    private final ImageView[] slotLine3 = new ImageView[5];

    private int betL;
    private final int[] lineComp = new int[2];
    private final DecimalFormat decimalFormat = new DecimalFormat( "#.#" );

    private static final ArrayList<Integer> idSlotsComp = new ArrayList<>();

    public void operation() {
        String tempStringBet = betCountView.getText().toString();
        String tempStringCoin = coinCountView.getText().toString();

        double tempDoubleCoin = Double.parseDouble(tempStringCoin);
        double tempDoubleBet = Double.parseDouble(tempStringBet);

        ArrayList<Integer> line1Array = new ArrayList<>();
        line1Array.add(Spin.getSlotColumnId1()[0]);
        line1Array.add(Spin.getSlotColumnId2()[0]);
        line1Array.add(Spin.getSlotColumnId3()[0]);
        line1Array.add(Spin.getSlotColumnId4()[0]);
        line1Array.add(Spin.getSlotColumnId5()[0]);

        ArrayList<Integer> line2Array = new ArrayList<>();
        line2Array.add(Spin.getSlotColumnId1()[1]);
        line2Array.add(Spin.getSlotColumnId2()[1]);
        line2Array.add(Spin.getSlotColumnId3()[1]);
        line2Array.add(Spin.getSlotColumnId4()[1]);
        line2Array.add(Spin.getSlotColumnId5()[1]);

        ArrayList<Integer> line3Array = new ArrayList<>();
        line3Array.add(Spin.getSlotColumnId1()[2]);
        line3Array.add(Spin.getSlotColumnId2()[2]);
        line3Array.add(Spin.getSlotColumnId3()[2]);
        line3Array.add(Spin.getSlotColumnId4()[2]);
        line3Array.add(Spin.getSlotColumnId5()[2]);

        setLineResult(line1Array);
        if (idSlotsComp.size() > 1) {
            for (int i = 0; i < idSlotsComp.size(); i++) {
                slotCompAnimation(slotLine1[idSlotsComp.get(i)], 200);
            }
        }
        tempDoubleCoin = tempDoubleCoin + (tempDoubleBet * betL);
        line1Result.setText(decimalFormat.format(tempDoubleBet * betL));
        textViewLRAnimation(line1Result, 2000);

        setLineResult(line2Array);
        if (idSlotsComp.size() > 1) {
            for (int i = 0; i < idSlotsComp.size(); i++) {
                slotCompAnimation(slotLine2[idSlotsComp.get(i)], 200);
            }
        }
        tempDoubleCoin = tempDoubleCoin + (tempDoubleBet * betL);
        line2Result.setText(decimalFormat.format(tempDoubleBet * betL));
        textViewLRAnimation(line2Result, 2000);

        setLineResult(line3Array);
        if (idSlotsComp.size() > 1) {
            for (int i = 0; i < idSlotsComp.size(); i++) {
                slotCompAnimation(slotLine3[idSlotsComp.get(i)], 200);
            }
        }
        line3Result.setText(decimalFormat.format(tempDoubleBet * betL));
        textViewLRAnimation(line3Result, 2000);

        tempDoubleCoin = tempDoubleCoin + (tempDoubleBet * betL);

        tempStringCoin = decimalFormat.format(tempDoubleCoin);
        coinCountView.setText(tempStringCoin);
    }

    public void setLineResult(ArrayList<Integer> line) {
        Set<Integer> duplicatedL1 = line.stream().filter(n -> line.stream().filter(x -> x.equals(n)).count() > 1).collect(Collectors.toSet());
        int[] arrayL = duplicatedL1.stream().mapToInt(Number::intValue).toArray();
        int lineResult;
        if (arrayL.length <= 0) {
            idSlotsComp.clear();
            lineResult = 0;
        } else if (arrayL.length == 1) {
            lineComp[0] = 1;
            idSlotsComp.clear();
            for (int y = 0; y < line.size() - 1; y++) {
                if (arrayL[0] == line.get(y) && line.get(y).equals(line.get(y + 1))) {
                    lineComp[0] =  lineComp[0] + 1;

                    idSlotsComp.add(y);
                    idSlotsComp.add(y + 1);
                }
            }
            if (lineComp[0] == 1) {
                lineResult = 0;
            } else {
                lineResult = lineComp[0];
            }

        } else {
            idSlotsComp.clear();
            lineComp[0] = 1;
            lineComp[1] = 1;
            for (int i = 0; i < arrayL.length; i++) {
                int a = arrayL[i];
                for (int y = 0; y < line.size() - 1; y++) {
                    if (a == line.get(y) && line.get(y).equals(line.get(y + 1))) {
                        lineComp[i] =  lineComp[i] + 1;

                        idSlotsComp.add(y);
                        idSlotsComp.add(y + 1);
                    }
                }
            }
            if (lineComp[0] >= lineComp[1]){
                lineResult = lineComp[0];
            } else if (lineComp[0] == 1 || lineComp[1] == 1){
                lineResult = 0;
            } else {
                lineResult = lineComp[1];
            }
        }

        switch (lineResult) {
            case (0):
                betL = -1;
                break;
            case (2):
                betL = 2;
                break;
            case (3):
                betL = 4;
                break;
            case (4):
                betL = 6;
                break;
            case (5):
                betL = 10;
                break;
        }
    }

    public void upBetCount() {
        String tempStringBet = betCountView.getText().toString();
        double tempDoubleBet = Double.parseDouble(tempStringBet);
        tempDoubleBet = tempDoubleBet + 1;
        tempStringBet = decimalFormat.format(tempDoubleBet);
        betCountView.setText(tempStringBet);
    }

    public void lowBetCount() {
        String tempStringBet = betCountView.getText().toString();
        double tempDoubleBet = Double.parseDouble(tempStringBet);
        if (tempDoubleBet > 1) {
            tempDoubleBet = tempDoubleBet - 1;
        }
        tempStringBet = decimalFormat.format(tempDoubleBet);
        betCountView.setText(tempStringBet);
    }

    public void reset() {
        String tempStringCoin = "10";
        coinCountView.setText(tempStringCoin);
        String tempStringBet = "1";
        betCountView.setText(tempStringBet);
    }

    public ImageView[] getSlotLine1 () {
        return slotLine1;
    }

    public ImageView[] getSlotLine2 () {
        return slotLine2;
    }

    public ImageView[] getSlotLine3 () {
        return slotLine3;
    }

    public DecimalFormat getDecimalFormat () {
        return decimalFormat;
    }

}
