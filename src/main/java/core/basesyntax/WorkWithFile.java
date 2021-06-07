package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply,";
    private static final String BUY = "buy,";
    private static final String RESULT = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        String infoFromFile = readFromFile(fromFileName);
        String report = createReport(infoFromFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String readFromFile(String infoFromFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(infoFromFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char)value);
                value = bufferedReader.read();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private String createReport(String infoFromFile) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String[] splittedFile = infoFromFile.replace("\r", "").split("\n");
        for (String splittedData : splittedFile) {
            String[] splittedLine = splittedData.split(",");
            if (splittedLine[OPERATION_TYPE_INDEX].equals("supply")) {
                supplyAmount += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            } else {
                buyAmount += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(supplyAmount)
                .append(System.lineSeparator()).append(BUY).append(buyAmount)
                .append(System.lineSeparator()).append(RESULT).append(supplyAmount - buyAmount);
        return stringBuilder.toString();
    }
}
