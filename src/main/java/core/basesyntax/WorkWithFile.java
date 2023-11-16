package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int COLUMN_WITH_TYPE = 0;
    public static final int COLUMN_WITH_SUM = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String stringFromFile = stringFromFile(fromFileName);
        String report = calculateReport(stringFromFile);
        writeToFile(toFileName, report);
    }

    private String stringFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder builder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't reas a file " + fileName, e);
        }
    }

    private String calculateReport(String stringCsv) {
        String[] linesArray = stringCsv.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String line : linesArray) {
            String[] lineData = line.split(",");
            int lineNumber = Integer.parseInt(lineData[COLUMN_WITH_SUM]);
            if (lineData[COLUMN_WITH_TYPE].equals("supply")) {
                supplySum += lineNumber;
            } else {
                buySum += lineNumber;
            }
        }

        StringBuilder stringBuilder = new StringBuilder()
                .append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(supplySum - buySum);

        return stringBuilder.toString();
    }

    private void writeToFile(String fileName, String stringToWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(stringToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
