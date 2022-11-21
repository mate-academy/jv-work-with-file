package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OPERATION_TYPE = 0;
    private static final int INDEX_OPERATION_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            String data = stringBuilder.toString();
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }

    private String createReport(String data) {
        String[] dataFromFile = data.split(System.lineSeparator());
        int amountSupply = 0;
        int amountBuy = 0;
        for (String text : dataFromFile) {
            String[] dataFromFileNew = text.split(",");
            if (dataFromFileNew[INDEX_OPERATION_TYPE].equals("supply")) {
                amountSupply = amountSupply
                        + Integer.parseInt(dataFromFileNew[INDEX_OPERATION_AMOUNT]);
            } else {
                amountBuy = amountBuy + Integer.parseInt(dataFromFileNew[INDEX_OPERATION_AMOUNT]);
            }
        }
        int result = amountSupply - amountBuy;
        String reportString = "supply," + amountSupply + System.lineSeparator()
                + "buy," + amountBuy + System.lineSeparator()
                + "result," + result;
        return reportString;
    }

    private void writeToFile(String reportString, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(reportString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
