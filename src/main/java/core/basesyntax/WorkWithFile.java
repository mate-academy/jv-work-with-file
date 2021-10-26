package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int OPERATION_COLUMN = 0;
    private static final int VALUE_COLUMN = 1;

    private int supplyValue = 0;
    private int buyValue = 0;

    public WorkWithFile() {
    }

    private void countValuesFromString(String[] valueToArray) {
        switch (valueToArray[OPERATION_COLUMN]) {
            case BUY: {
                this.buyValue += Integer.valueOf(valueToArray[VALUE_COLUMN]);
                break;
            }
            case SUPPLY: {
                this.supplyValue += Integer.valueOf(valueToArray[VALUE_COLUMN]);
                break;
            } default : break;
        }
    }

    private void readValuesFromFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            String value = reader.readLine();
            while (value != null) {
                countValuesFromString(value.split("\\,"));
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeValuesToFile(String toFileName) {
        File fileTo = new File(toFileName);

        try {
            fileTo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo))) {
            StringBuilder stringToWrite = new StringBuilder();
            stringToWrite.append(SUPPLY).append(",").append(this.supplyValue)
                    .append(System.lineSeparator())
                    .append(BUY).append(",").append(this.buyValue)
                    .append(System.lineSeparator())
                    .append(RESULT).append(",").append(this.supplyValue - this.buyValue);
            writer.write(stringToWrite.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readValuesFromFile(fromFileName);
        writeValuesToFile(toFileName);
    }
}
