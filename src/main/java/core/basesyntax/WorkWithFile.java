package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fileData = readFromFile(fromFileName);
        Map<String, Integer> reportResult = createReport(fileData);
        writeReportToFile(toFileName, reportResult);
    }

    private Map<String, Integer> createReport(List<String> fileRows) {
        Map<String, Integer> reportValue = new HashMap<>();
        for (String fileRow : fileRows) {
            String[] nameAndPrice = fileRow.split(",");
            String fruitName = nameAndPrice[0];
            final int price = Integer.parseInt(nameAndPrice[1]);

            if (reportValue.containsKey(fruitName)) {
                Integer sum = reportValue.get(fruitName) + price;
                reportValue.put(fruitName, sum);
            } else {
                reportValue.put(fruitName, price);
            }
        }
        return reportValue;
    }

    private List<String> readFromFile(String fromFileName) {
        String value;
        List<String> fileRows = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((value = bufferedReader.readLine()) != null) {
                fileRows.add(value);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        return fileRows;
    }

    private static void writeReportToFile(String toFileName, Map<String, Integer> map) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int result = map.get("supply") - map.get("buy");
            bufferedWriter.write("supply" + "," + map.get("supply") + System.lineSeparator());
            bufferedWriter.write("buy" + "," + map.get("buy") + System.lineSeparator());
            bufferedWriter.write("result" + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file " + toFileName, e);
        }
    }
}
