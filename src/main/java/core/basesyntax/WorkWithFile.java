package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WorkWithFile {
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String RESULT_KEY = "result";
    private static final String CSV_SPLIT_KEY = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> hashMap = readAndProcessData(fromFileName);
        int supplyTotal = hashMap.getOrDefault(SUPPLY_KEY, 0);
        int buyTotal = hashMap.getOrDefault(BUY_KEY, 0);
        int resultTotal = calculateResult(supplyTotal, buyTotal);
        writeToFile(toFileName, supplyTotal, buyTotal, resultTotal);
    }

    public HashMap<String, Integer> readAndProcessData(String fileName) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processLine(line, hashMap);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file! " + fileName + e);
        }
        return hashMap;
    }

    private void processLine(String line, HashMap<String, Integer> hashMap) {
        String[] values = line.split(CSV_SPLIT_KEY);
        String operationType = values[OPERATION_TYPE_INDEX];
        int tempAmount = Integer.parseInt(values[VALUE_INDEX]);
        hashMap.put(operationType, hashMap.getOrDefault(operationType, 0) + tempAmount);
    }

    private int calculateResult(int supplySum, int buySum) {
        return supplySum - buySum;
    }

    private void writeToFile(String toFileName, int supplyTotal, int buyTotal, int resultTotal) {
        File resultFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile))) {
            bufferedWriter.write(SUPPLY_KEY + CSV_SPLIT_KEY + supplyTotal);
            bufferedWriter.newLine();
            bufferedWriter.write(BUY_KEY + CSV_SPLIT_KEY + buyTotal);
            bufferedWriter.newLine();
            bufferedWriter.write(RESULT_KEY + CSV_SPLIT_KEY + resultTotal);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName + e);
        }
    }
}
