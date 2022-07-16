package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFileToString(fromFileName);
        writeToFile(toFileName, createReport(data));
    }

    private String readFromFileToString(String filename) {
        File file = new File(filename);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String data = bufferedReader.readLine();
            StringBuilder builder = new StringBuilder();
            while (data != null) {
                builder.append(data).append(" ");
                data = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + filename, e);
        }
    }

    private String createReport(String data) {
        int supplySum = 0;
        int buySum = 0;
        String[] splittedLines = data.split(" ");
        for (String value : splittedLines) {
            String[] splittedData = value.split(COMMA_SEPARATOR);
            switch (splittedData[OPERATION_TYPE_INDEX]) {
                case "supply":
                    supplySum += Integer.parseInt(splittedData[AMOUNT_INDEX]);
                    break;
                case "buy":
                    buySum += Integer.parseInt(splittedData[AMOUNT_INDEX]);
                    break;
                default:
            }
        }
        StringBuilder resultReport = new StringBuilder();
        resultReport.append("supply").append(COMMA_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append("buy").append(COMMA_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append("result").append(COMMA_SEPARATOR).append(supplySum - buySum);
        return resultReport.toString();
    }

    private void writeToFile(String filename, String data) {
        File myFile = new File(filename);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(myFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file " + filename, e);
        }
    }
}
