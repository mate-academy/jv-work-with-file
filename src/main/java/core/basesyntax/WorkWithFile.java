package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
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
            String[] dataArray = data.split(",");
            String operation = dataArray[0].trim();
            String amountStr = dataArray[1].trim();
            int amount = Integer.parseInt(amountStr);
            if ("supply".equals(operation)) {
                supplyCounter += amount;
            } else if ("buy".equals(operation)) {
                buyCounter += amount;
            }
        }

        stringBuilder.append("supply,").append(supplyCounter).append(System.lineSeparator())
                     .append("buy,").append(buyCounter).append(System.lineSeparator())
                     .append("result,").append(supplyCounter - buyCounter);

        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
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
            throw new RuntimeException("Can't read from file", e);
        }
        String readedString = stringWithData.toString();
        return readedString.split("\n");
    }
}
