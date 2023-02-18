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
    private static final String SEPARATOR = ",";
    private static final int INDEX_NAME = 0;
    private static final int INDEX_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        String report = createReport(readFromFile(fromFile));
        writeToFile(toFile, report);
    }

    private String readFromFile(File fromFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            StringBuilder result = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] lineArray = line.split(SEPARATOR);
                if (lineArray.length != 2) {
                    throw new RuntimeException("Can't read file: " + fromFile);
                }
                result.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine(); 
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFile, e);
        }
    }

    private String createReport(String data) {
        int supplyCount = 0;
        int buyCount = 0;
        int result;
        String[] dataArray = data.split(System.lineSeparator());
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : dataArray) {
            String[] lineArray = line.split(SEPARATOR);
            if (lineArray[INDEX_NAME].equals(SUPPLY)) {
                supplyCount += Integer.parseInt(lineArray[INDEX_AMOUNT]);
            } else if (lineArray[INDEX_NAME].equals(BUY)) {
                buyCount += Integer.parseInt(lineArray[INDEX_AMOUNT]);
            }
        }
        result = supplyCount - buyCount;
        stringBuilder.append("supply,").append(supplyCount).append(System.lineSeparator())
                .append("buy,").append(buyCount).append(System.lineSeparator())
                .append("result,").append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(File toFile, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFile, e);
        }
    }
}
