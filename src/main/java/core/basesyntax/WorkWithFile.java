package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String NEW_LINE = "\n";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String createReport(String[] arrayWithData) {
        StringBuilder stringBuilder = new StringBuilder();
        int supplyCounter = 0;
        int buyCounter = 0;
        for (String data : arrayWithData) {
            String[] dataArray = data.split(COMMA);
            String operation = dataArray[0].trim();
            String amountStr = dataArray[1].trim();
            int amount = Integer.parseInt(amountStr);
            if (SUPPLY.equals(operation)) {
                supplyCounter += amount;
            } else if (BUY.equals(operation)) {
                buyCounter += amount;
            }
        }

        return new StringBuilder()
                .append(SUPPLY).append(",").append(supplyCounter).append(System.lineSeparator())
                .append(BUY).append(",").append(buyCounter).append(System.lineSeparator())
                .append(RESULT).append(",").append(supplyCounter - buyCounter).toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringWithData = new StringBuilder();
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            readLine = bufferedReader.readLine();
            while (readLine != null) {
                stringWithData.append(readLine).append(System.lineSeparator());
                readLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        String readedString = stringWithData.toString();
        return readedString.split(NEW_LINE);
    }
}
