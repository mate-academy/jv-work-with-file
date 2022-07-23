package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        writeToFile(toFileName, createReport(data));
    }

    private String readFromFile(String filename) {
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
        String[] lines = data.split(" ");
        for (String line : lines) {
            String[] splittedLine = line.split(LINE_SEPARATOR);
            switch (splittedLine[OPERATION_TYPE_INDEX]) {
                case "supply":
                    supplySum += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                    break;
                case "buy":
                    buySum += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                    break;
                default:
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply").append(LINE_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append("buy").append(LINE_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append("result").append(LINE_SEPARATOR).append(supplySum - buySum);
        return reportBuilder.toString();
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
