package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_WORD = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final String STRING_TO_COMPARE = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileContent = readFromFile(fromFileName);
        String report = createReport(fileContent);
        writeToFile(toFileName, report);
    }

    public String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can't read file", e);
        }
        return stringBuilder.toString().split(" ");
    }

    public String createReport(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String datum : data) {
            String[] datumArr = datum.split(",");
            int amount = Integer.parseInt(datumArr[INDEX_OF_AMOUNT]);
            if (datumArr[INDEX_OF_WORD].equals(STRING_TO_COMPARE)) {
                supplyAmount += amount;
            } else {
                buyAmount += amount;
            }
        }
        return stringBuilder.append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(supplyAmount - buyAmount).toString();
    }

    public void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("cant write to file", e);
        }
    }
}
