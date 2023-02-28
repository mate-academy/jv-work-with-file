package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY_TEXT = "supply";
    private static final String BUY_TEXT = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        File fileTo = new File(toFileName);
        int fullAmount;
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            String eachString = reader.readLine();
            while (eachString != null) {
                String[] stringParts = eachString.split(COMMA);
                String operationType = stringParts[0];
                int amount = Integer.parseInt(stringParts[1]);
                if (operationType.equals(SUPPLY_TEXT)) {
                    supplySum += amount;
                }
                if (operationType.equals(BUY_TEXT)) {
                    buySum += amount;
                }
                eachString = reader.readLine();
            }
            fullAmount = supplySum - buySum;
        } catch (IOException e) {
            throw new RuntimeException("File Not found! ", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo))) {
            StringBuilder writeString = new StringBuilder();
            String supply = (SUPPLY_TEXT + COMMA + supplySum + System.lineSeparator());
            String buy = (BUY_TEXT + COMMA + buySum + System.lineSeparator());
            writeString.append(supply).append(buy).append(RESULT).append(COMMA).append(fullAmount);
            writer.write(String.valueOf(writeString));
        } catch (IOException e) {
            throw new RuntimeException("No such file ... ", e);
        }
    }
}

