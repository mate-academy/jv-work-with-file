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
    private static final String RESULT = "result";
    private static final int PRICE = 1;
    private static final int OPERATION = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(getFileReport(fromFileName), toFileName);
    }

    private String getFileReport(String fromFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        StringBuilder report = new StringBuilder();
        File fromFile = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] readedFromLine = value.split(",");
                if (readedFromLine[OPERATION].equals(SUPPLY)) {
                    supplyCount += Integer.parseInt(readedFromLine[PRICE]);
                } else {
                    buyCount += Integer.parseInt(readedFromLine[PRICE]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e);
        }
        report.append(SUPPLY).append(",").append(supplyCount).append(System.lineSeparator())
                .append(BUY).append(",").append(buyCount).append(System.lineSeparator())
                .append(RESULT).append(",").append(supplyCount - buyCount);
        return report.toString();
    }

    private void writeToFile(String fileReport, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(fileReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + e);
        }
    }
}
