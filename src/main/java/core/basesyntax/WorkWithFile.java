package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_SYMBOL = ",";
    private static final int INDEX_OF_NAME = 0;
    private static final int INDEX_OF_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(readFromFile(fromFileName).toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file" + toFileName, e);
        }
    }

    private StringBuilder readFromFile(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] information = line.split(SPLIT_SYMBOL);
                if ("supply".equals(information[INDEX_OF_NAME])) {
                    supplySum += Integer.parseInt(information[INDEX_OF_VALUE]);
                } else {
                    buySum += Integer.parseInt(information[INDEX_OF_VALUE]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t find or read file" + fromFileName, e);
        }
        return crateTabular(supplySum, buySum);
    }

    private StringBuilder crateTabular(int supplyCounter, int buyCounter) {
        StringBuilder result = new StringBuilder();
        result.append("supply,").append(supplyCounter).append(System.lineSeparator());
        result.append("buy,").append(buyCounter).append(System.lineSeparator());
        result.append("result,").append(supplyCounter - buyCounter);
        return result;
    }
}
