package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SEPARATOR = " ";
    private static final String COMA = ",";
    private static final int OPERATION_POS = 0;
    private static final int AMOUNT_POS = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readDataFromFile(fromFileName);
        String report = makeReportFromData(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readDataFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(SEPARATOR);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file" + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String makeReportFromData(String data) {
        int supplyAmount = 0;
        int buyAmount = 0;
        int result = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] dataArray = data.split(SEPARATOR);
        for (String datum : dataArray) {
            String[] operation = datum.split(COMA);
            switch (operation[OPERATION_POS]) {
                case SUPPLY:
                    supplyAmount += Integer.parseInt(operation[AMOUNT_POS]);
                    break;
                case BUY:
                    buyAmount += Integer.parseInt(operation[AMOUNT_POS]);
                    break;
                default:
            }
        }
        result = supplyAmount - buyAmount;
        stringBuilder.append(SUPPLY).append(COMA).append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY).append(COMA).append(buyAmount).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(result);
        return stringBuilder.toString();
    }

    private void writeReportToFile(String report, String fileName) {
        try {
            Files.write(Path.of(fileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
