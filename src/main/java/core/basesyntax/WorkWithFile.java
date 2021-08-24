package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_TYPE = 0;
    public static final int AMOUNT = 1;
    public static final int SUPPLY_AMOUNT = 0;
    public static final int BUY_AMOUNT = 1;
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String result = createReport(calculateValue(dataFromFile));
        writeToFile(toFileName, result);

    }

    private String[] readFromFile(String fileName) {
        StringBuilder someData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int value = reader.read();
            while (value != -1) {
                someData.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file");
        }
        return someData.toString().split("\\R");
    }

    private int[] calculateValue(String[] arr) {
        int buyNumber = 0;
        int supplyNumber = 0;
        for (int i = 0; i < arr.length; i++) {
            String[] data = arr[i].split(",");
            if (data[OPERATION_TYPE].equals("buy")) {
                buyNumber += Integer.parseInt(data[AMOUNT]);
            } else {
                supplyNumber += Integer.parseInt(data[AMOUNT]);
            }
        }
        return new int[] {supplyNumber,buyNumber};
    }

    private String createReport(int[] arr) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY + ",")
                .append(arr[SUPPLY_AMOUNT])
                .append(System.lineSeparator())
                .append(BUY + ",")
                .append(arr[BUY_AMOUNT])
                .append(System.lineSeparator())
                .append(RESULT + ",")
                .append(arr[SUPPLY_AMOUNT] - arr[BUY_AMOUNT]);
        return report.toString();
    }

    private void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file");
        }
    }
}
