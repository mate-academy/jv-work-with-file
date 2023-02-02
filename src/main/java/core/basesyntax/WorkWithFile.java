package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int SEARCH_BY_INDEX = 0;
    private static final int COUNT_BY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFromFile(fromFileName);
        String report = createReport(fileData);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String readFile) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read data from file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data to file", e);
        }
        return builder.toString();
    }

    private String createReport(String readString) {
        int supplyCount = 0;
        int buyCount = 0;
        String[] informationRead = readString.split(System.lineSeparator());
        for (String infoFromFile : informationRead) {
            String[] listFromFile = infoFromFile.split(",");
            for (int i = 1; i < listFromFile.length; i++) {
                if (listFromFile[SEARCH_BY_INDEX].equals(SUPPLY)) {
                    supplyCount += Integer.parseInt(listFromFile[COUNT_BY_INDEX]);
                } else {
                    buyCount += Integer.parseInt(listFromFile[COUNT_BY_INDEX]);
                }
            }
        }
        int result = supplyCount - buyCount;
        return report(supplyCount, buyCount, result);
    }

    private String report(int supplyCount, int buyCount, int result) {
        return SUPPLY
                + "," + supplyCount
                + System.lineSeparator()
                + BUY + ","
                + buyCount
                + System.lineSeparator()
                + RESULT + ","
                + result;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
