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

    public void getStatistic(String fromFileName, String toFileName) {
        String stringToWrite = getDataToWriteFromFile(fromFileName);
        writeValuesToFile(toFileName, stringToWrite);
    }

    private String getDataToWrite(int supplyValue, int buyValue) {
        StringBuilder stringToWrite = new StringBuilder();
        stringToWrite.append(SUPPLY).append(",").append(supplyValue)
                .append(System.lineSeparator())
                .append(BUY).append(",").append(buyValue)
                .append(System.lineSeparator())
                .append(RESULT).append(",").append(supplyValue - buyValue);
        return stringToWrite.toString();
    }

    private String getDataToWriteFromFile(String fromFileName) {
        int supplyValue = 0;
        int buyValue = 0;
        File fileFrom = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            String value = reader.readLine();
            while (value != null) {
                String[] valueToArray = value.split(",");
                switch (valueToArray[OPERATION_COLUMN]) {
                    case BUY: {
                        buyValue += Integer.valueOf(valueToArray[VALUE_COLUMN]);
                        break;
                    }
                    case SUPPLY: {
                        supplyValue += Integer.valueOf(valueToArray[VALUE_COLUMN]);
                        break;
                    } default : break;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return getDataToWrite(supplyValue, buyValue);
    }

    private void writeValuesToFile(String toFileName, String stringToWrite) {
        File fileTo = new File(toFileName);
        try {
            fileTo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo))) {
            writer.write(stringToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
