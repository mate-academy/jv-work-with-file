package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }
        return builder.toString();
    }

    private String createReport(String text) {
        int summSupplies = 0;
        int summPurchases = 0;
        String[] lines = text.split(System.lineSeparator());
        for (String words : lines) {
            String[] resultSplited = words.split(",");
            if (resultSplited[OPERATION_INDEX].equals("supply")) {
                summSupplies += Integer.parseInt(resultSplited[QUANTITY_INDEX]);
            } else {
                summPurchases += Integer.parseInt(resultSplited[QUANTITY_INDEX]);
            }
        }
        int result = summSupplies - summPurchases;

        return "supply," + summSupplies + System.lineSeparator()
                + "buy," + summPurchases + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(String toFileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file " + toFileName, e);
        }
    }
}
