package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LifeHackCalculus {
    private static final int LINE_SEPARATOR_VALUE = 10;
    private static final int SMALL_B_VALUE = 98;
    private static final int SMALL_S_VALUE = 115;
    private static final int SKIP_IF_S = 6;
    private static final int SKIP_IF_B = 3;

    private int supply;
    private int buy;
    private int result;
    private final StringBuilder stringBuilder = new StringBuilder();

    public void getCalculations(String fromFileName) {
        int supply = 0;
        int buy = 0;
        boolean flag = true;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fromFileName));
            int value = br.read();
            while (value != -1) {
                if (value == SMALL_S_VALUE) {
                    br.skip(SKIP_IF_S);
                    value = br.read();
                    flag = true;
                }
                if (value == SMALL_B_VALUE) {
                    br.skip(SKIP_IF_B);
                    value = br.read();
                    flag = false;
                }
                if (value != LINE_SEPARATOR_VALUE) {
                    stringBuilder.append((char) value);
                    value = br.read();
                } else if (flag) {
                    supply += Integer.parseInt(stringBuilder.toString());
                    stringBuilder.setLength(0);
                    value = br.read();
                } else {
                    buy += Integer.parseInt(stringBuilder.toString());
                    stringBuilder.setLength(0);
                    value = br.read();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Check your file " + fromFileName, e);
        }
        this.supply = supply;
        this.buy = buy;
        this.result = supply - buy;
    }

    public int getSupply() {
        return supply;
    }

    public int getBuy() {
        return buy;
    }

    public int getResult() {
        return result;
    }
}
