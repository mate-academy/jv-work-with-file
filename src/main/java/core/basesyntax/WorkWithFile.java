package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int amountOfBuy = 0;
    private int amountOfSupply = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readFromFile(fromFileName)));
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String data = reader.readLine();
            while (data != null) {
                builder.append(data).append(System.lineSeparator());
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);

        }
        return builder.toString();
    }

    private String createReport(String dataFromFile) {

        String[] dataArray = dataFromFile.split(System.lineSeparator());
        String buyOperation = "buy";
        String supplyOperation = "supply";
        for (String datum : dataArray) {
            String[] splittedData = datum.split(",");
            int indexOfOperation = 0;
            int indexOfAmount = 1;
            if (splittedData[indexOfOperation].equals(buyOperation)) {
                amountOfBuy += Integer.parseInt(splittedData[indexOfAmount]);
            }
            if (splittedData[indexOfOperation].equals(supplyOperation)) {
                amountOfSupply += Integer.parseInt(splittedData[indexOfAmount]);
            }
        }
        return supplyOperation + ","
                    + amountOfSupply + System.lineSeparator()
                    + buyOperation + ","
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
            throw new RuntimeException("Can't read the file", e);
        }
    }
}
