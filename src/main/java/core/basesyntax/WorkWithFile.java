package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {

    private Report report;
    private static final String DATA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> operationAmountMap = new HashMap<>();
        fillMapWithKeys(operationAmountMap);
        operationAmountMap = readDataFromFileIntoMap(fromFileName);
        writeStatisticReportIntoFile(toFileName, operationAmountMap);
    }

    private void fillMapWithKeys(Map<String, Integer> operationAmountMap) {
        operationAmountMap.put("buy", 0);
        operationAmountMap.put("supply", 0);
    }

    private Map<String, Integer> readDataFromFileIntoMap(String fromFileName) {
        Map<String, Integer> operationAmountMap = new HashMap<>();
        fillMapWithKeys(operationAmountMap);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String dataLine = bufferedReader.readLine();
            while (dataLine != null) {
                String[] operationAmount = dataLine.split(DATA_SEPARATOR);
                String operation = operationAmount[0];
                int amount = Integer.parseInt(operationAmount[1]);
                int currentAmount = operationAmountMap.get(operation);
                if (operationAmountMap.containsKey(operation)) {
                    operationAmountMap.put(operation, currentAmount + amount);
                }
                dataLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        return operationAmountMap;
    }

    private void writeStatisticReportIntoFile(String toFileName, Map<String,
            Integer> operationAmountMap) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(toFileName))) {
             report = new Report(operationAmountMap.get("supply"),
                    operationAmountMap.get("buy"));
            String reportString = report.createReportString();
            bufferedWriter.write(reportString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data into the file", e);
        }
    }
}
