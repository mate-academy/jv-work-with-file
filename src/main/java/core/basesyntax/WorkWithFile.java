package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply = 0;
    private int buy = 0;
    private static final String SUPPLY_NAME = "supply";
    private static final String BUY_NAME = "buy";
    private static final String RESULT_NAME = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void addBuy(int buyAmount) {
        buy += buyAmount;
    }

    public void addSupply(int supplyAmount) {
        supply += supplyAmount;
    }

    public int getResult() {
        return supply - buy;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }


    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("read error!" + e);
        }
        String[] infoReport = builder.toString().split("\r?\n|\r");
        String[] infoCell = new String[2];
        for (String infoElement: infoReport) {
            infoCell = infoElement.split(",");
            if (infoCell[OPERATION_INDEX ].equals(SUPPLY_NAME)) {
                addSupply(Integer.parseInt(infoCell[AMOUNT_INDEX]));
            } else if (infoCell[OPERATION_INDEX ].equals(BUY_NAME)) {
                addBuy(Integer.parseInt(infoCell[AMOUNT_INDEX]));
            }
        }
        StringBuilder finalBuilder = new StringBuilder();
        finalBuilder.append(SUPPLY_NAME + ","
                + getSupply() + System.lineSeparator()
                + BUY_NAME + ","
                + getBuy() + System.lineSeparator()
                + RESULT_NAME + ","
                + getResult() + System.lineSeparator());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(finalBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("write error!" + e);
        }
    }
}
