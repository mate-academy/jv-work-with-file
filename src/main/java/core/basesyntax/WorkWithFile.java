package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_RESULT_PART = 0;
    private static final int BUY_RESULT_PART = 1;
    private static final int RESULT_PART = 2;
    private static final int OPERATION_PART = 0;
    private static final int AMOUNT_PART = 1;
    private static final String SEPARATOR = ",";
    private static final String SUPPLY_OPERATION = "s";
    private static final String BUY_OPERATION = "b";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return data.toString();
    }

    private String createReport(String dataFromFile) {
        int[] result = calculateReport(dataFromFile);
        return "supply," + result[SUPPLY_RESULT_PART] + System.lineSeparator()
                + "buy," + result[BUY_RESULT_PART] + System.lineSeparator()
                + "result," + result[RESULT_PART];
    }

    private int[] calculateReport(String dataFromFile) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String[] lines = dataFromFile.split(System.lineSeparator());
        for (String line : lines) {
            int[] amounts = separateSum(line);
            supplyAmount += amounts[SUPPLY_RESULT_PART];
            buyAmount += amounts[BUY_RESULT_PART];
        }
        int result = supplyAmount - buyAmount;
        return new int[]{supplyAmount, buyAmount, result};
    }

    private int[] separateSum(String line) {
        String[] words = line.split(SEPARATOR);
        String wordPart = words[OPERATION_PART];
        String numberPart = words[AMOUNT_PART];
        int supplyAmount = 0;
        int buyAmount = 0;
        if (wordPart.startsWith(SUPPLY_OPERATION)) {
            supplyAmount = Integer.parseInt(numberPart);
        }
        if (wordPart.startsWith(BUY_OPERATION)) {
            buyAmount = Integer.parseInt(numberPart);
        }
        return new int[]{supplyAmount, buyAmount};
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
