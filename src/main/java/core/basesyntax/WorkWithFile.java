package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile;
        File file = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            dataFromFile = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] separatedValue = dataFromFile.split("\n");
        int buySummary = 0;
        int supplySummary = 0;
        for (String dat : separatedValue) {
            String[] dataComponents = dat.split(COMA);
            String operationType = dataComponents[OPERATION_TYPE_INDEX];
            int money = Integer.parseInt(dataComponents[AMOUNT_INDEX]);
            if (operationType.equals(BUY)) {
                buySummary = buySummary + money;
            }
            if (operationType.equals(SUPPLY)) {
                supplySummary = supplySummary + money;
            }
        }
        int result = supplySummary - buySummary;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(COMA).append(supplySummary).append(System.lineSeparator())
                .append(BUY).append(COMA).append(buySummary).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(result);
        String reportToFile = report.toString();
        File fileReport = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileReport))) {
            bufferedWriter.write(reportToFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
