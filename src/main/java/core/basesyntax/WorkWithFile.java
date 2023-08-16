package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String SEPARATION_SIGN = ",";
    private static final String RESULT_WORD = "result";
    private static final int TYPE_OF_OPERATION_INDEX = 0;
    private static final int AMOUNT_OF_OPERATION_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String finalReport = createReport(data);
        writeToFile(finalReport, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file with name " + fromFileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] data) {
        StringBuilder report = new StringBuilder();
        int buyAmount = 0;
        int supplyAmount = 0;
        for (int i = 0; i < data.length; i++) {
            String[] splitData = data[i].split(SEPARATION_SIGN);
            if (splitData[TYPE_OF_OPERATION_INDEX].equals(BUY_WORD)) {
                buyAmount += Integer.parseInt(splitData[AMOUNT_OF_OPERATION_INDEX]);
            } else if (splitData[TYPE_OF_OPERATION_INDEX].equals(SUPPLY_WORD)) {
                supplyAmount += Integer.parseInt(splitData[AMOUNT_OF_OPERATION_INDEX]);
            }
        }
        int result = 0;
        result = supplyAmount - buyAmount;
        report new.append(SUPPLY_WORD).append(SEPARATION_SIGN).append(supplyAmount).append(System.lineSeparator())
                .append(BUY_WORD).append(SEPARATION_SIGN).append(buyAmount).append(System.lineSeparator())
                .append(RESULT_WORD).append(SEPARATION_SIGN).append(result);
        return report.toString();
    }

    private void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write a file : " + toFileName, e);
        }
    }
}
