package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private int amountOfBuy = 0;
    private int amountOfSupply = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file: " + fileName, e);
        }
        return builder.toString();
    }

    private String createReport(String data) {
        String[] dataArray = data.split(System.lineSeparator());
        for (String datum : dataArray) {
            String[] splittedData = datum.split(",");
            if (splittedData[INDEX_OF_OPERATION].equals(BUY_OPERATION)) {
                amountOfBuy += Integer.parseInt(splittedData[INDEX_OF_AMOUNT]);
            }
            if (splittedData[INDEX_OF_OPERATION].equals(SUPPLY_OPERATION)) {
                amountOfSupply += Integer.parseInt(splittedData[INDEX_OF_AMOUNT]);
            }
        }
        return SUPPLY_OPERATION + ","
                    + amountOfSupply + System.lineSeparator()
                    + BUY_OPERATION + ","
                    + amountOfBuy + System.lineSeparator()
                    + "result" + ","
                    + (amountOfSupply - amountOfBuy);
    }

    private void writeToFile(String fileName, String report) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write(report);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + fileName, e);
        }
    }
}
