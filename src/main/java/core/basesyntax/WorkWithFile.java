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
    private static final String DATA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readFileData = readFile(fromFileName);
        String report = createReport(readFileData);
        writeFile(toFileName, report);
    }

    private String createReport(String[] readedData) {
        StringBuilder stringBuilder = new StringBuilder();
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String data : readedData) {
            String[] splitData = data.split(DATA_SEPARATOR);
            if (splitData[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supplyAmount += Integer.parseInt(splitData[AMOUNT_INDEX]);
            } else if (splitData[OPERATION_TYPE_INDEX].equals(BUY)) {
                buyAmount += Integer.parseInt(splitData[AMOUNT_INDEX]);
            }
        }
        return stringBuilder
                .append("supply,")
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyAmount)
                .append(System.lineSeparator())
                .append("result,")
                .append(supplyAmount - buyAmount)
                .toString();
    }

    private String[] readFile(String fromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder
                        .append(line)
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + fromFile, e);
        }
        return stringBuilder
                .toString()
                .split(System.lineSeparator());
    }

    private void writeFile(String toFile, String report) {
        File file = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file: " + toFile, e);
        }
    }
}

