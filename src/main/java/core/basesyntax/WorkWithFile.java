package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final char SEPARATOR = ';';
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT_STR = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readDataFromFile(fromFileName);
        String report = createReport(dataFromFile);
        WorkWithFile.writeDataToFile(toFileName, report);
    }

    public String createReport(String[] dataForReport) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (int i = 0; i < dataForReport.length; i++) {
            String[] operationTypeAndAmount = dataForReport[i].split(",");
            String operationType = operationTypeAndAmount[0];
            int amount = Integer.parseInt(operationTypeAndAmount[1]);
            if (operationType.equals(SUPPLY)) {
                supplyAmount += amount;
            }
            if (operationType.equals(BUY)) {
                buyAmount += amount;
            }
        }
        int result = supplyAmount - buyAmount;
        return SUPPLY + "," + supplyAmount + System.lineSeparator()
                + BUY + "," + buyAmount + System.lineSeparator()
                + RESULT_STR + "," + result;
    }

    public String[] readDataFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value;
            while ((value = reader.readLine()) != null) {
                stringBuilder.append(value).append(SEPARATOR);
            }
            String[] dataFromFile = stringBuilder.toString().split(";");
            return dataFromFile;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
    }

    public static void writeDataToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't creat file", e);
        }
        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file", e);
        }
    }
}
