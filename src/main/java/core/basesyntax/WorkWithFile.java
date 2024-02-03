package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder resultBuilder = new StringBuilder();
        File formFile = new File(fromFileName);
        File toFile = new File(toFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(formFile))) {
            String line = reader.readLine();
            int supplyResult = 0;
            int buyResult = 0;
            while (line != null) {
                String[] data = line.split("\\W+");
                switch (data[OPERATION_TYPE_INDEX]) {
                    case SUPPLY:
                        supplyResult += Integer.parseInt(data[AMOUNT_INDEX]);
                        break;
                    case BUY:
                        buyResult += Integer.parseInt(data[AMOUNT_INDEX]);
                        break;
                    default:
                        break;
                }
                line = reader.readLine();
            }
            int result = supplyResult - buyResult;
            resultBuilder
                    .append(SUPPLY)
                    .append(DELIMITER)
                    .append(supplyResult)
                    .append(System.lineSeparator())
                    .append(BUY)
                    .append(DELIMITER)
                    .append(buyResult)
                    .append(System.lineSeparator())
                    .append(RESULT)
                    .append(DELIMITER)
                    .append(result)
                    .append(System.lineSeparator());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
                writer.write(resultBuilder.toString());
            } catch (IOException e) {
                throw new RuntimeException("Can`t write result to file", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
    }
}
