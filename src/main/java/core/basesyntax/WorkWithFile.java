package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMETER = ",";
    private static final int OPERATIONS = 0;
    private static final int AMOUNT_OF_NUMBERS = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] spreadSheet = readFromFileName(fromFileName);
        String report = report(spreadSheet);
        writeReportToFileName(toFileName, report);
    }

    private String[] readFromFileName(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String report(String[] csvFile) {
        int supplyAmount = 0;
        int buyAmount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String file : csvFile) {
            String[] files = file.split(DELIMETER);
            String operations = files[OPERATIONS];
            int amount = Integer.parseInt(files[AMOUNT_OF_NUMBERS]);

            if (operations.equals("supply")) {
                supplyAmount += amount;
            } else {
                buyAmount += amount;
            }
        }
        int result = supplyAmount - buyAmount;
        return stringBuilder.append(SUPPLY).append(DELIMETER).append(supplyAmount)
                .append(System.lineSeparator()).append(BUY).append(DELIMETER).append(buyAmount)
                .append(System.lineSeparator()).append(RESULT).append(DELIMETER).append(result)
                .toString();
    }

    private void writeReportToFileName(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
