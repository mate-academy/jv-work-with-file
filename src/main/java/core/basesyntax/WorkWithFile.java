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

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String data) {
        int amountOfBuy = 0;
        int amountOfSupply = 0;
        String[] dataArray = data.split(System.lineSeparator());
        for (String date : dataArray) {
            String [] splitedArray = date.split(",");
            if (splitedArray[INDEX_OF_OPERATION].equals(BUY_OPERATION)) {
                amountOfBuy += Integer.parseInt(splitedArray[INDEX_OF_AMOUNT]);
            }
            if (splitedArray[INDEX_OF_OPERATION].equals(SUPPLY_OPERATION)) {
                amountOfSupply += Integer.parseInt(splitedArray[INDEX_OF_AMOUNT]);
            }
        }
        return SUPPLY_OPERATION + "," + amountOfSupply + System.lineSeparator()
                + BUY_OPERATION + "," + amountOfBuy + System.lineSeparator()
                + "result" + "," + (amountOfSupply - amountOfBuy);
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file" + fileName, e);
        }
    }
}
