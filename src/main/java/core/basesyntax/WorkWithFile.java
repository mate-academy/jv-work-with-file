package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String SUPPLY_VALUE = "supply";
    private static final String BUY_VALUE = "buy";
    private static final String COMMA_SEPARATE = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = readFromFile(fromFileName);
        String report = createReport(textFromFile);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException("Can`t read file" + fromFileName, e);
        }
    }

    private String createReport(String text) {
        int resultSupply = 0;
        int resultBuy = 0;
        String[] lines = text.split(System.lineSeparator());
        for (String line: lines) {
            String[] splits = line.split(COMMA_SEPARATE);
            String operation = splits[OPERATION_INDEX];
            int amount = Integer.parseInt(splits[AMOUNT_INDEX]);
            if (operation.equals(SUPPLY_VALUE)) {
                resultSupply += amount;
            } else if (operation.equals(BUY_VALUE)) {
                resultBuy += amount;
            }
        }
        int result = resultSupply - resultBuy;
        return "supply," + resultSupply + System.lineSeparator()
                + "buy," + resultBuy + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can`t write to file" + toFileName, e);
        }
    }
}
