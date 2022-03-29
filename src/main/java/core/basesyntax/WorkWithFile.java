package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = 0;
        int supplySum = 0;
        String[] lines = readFromFile(fromFileName);
        for (String s : lines) {
            String[] line = s.split(SEPARATOR);
            switch (line[0]) {
                case SUPPLY_OPERATION:
                    supplySum += Integer.parseInt(line[1]);
                    break;
                case BUY_OPERATION:
                    buySum += Integer.parseInt(line[1]);
                    break;
                default:
                    throw new RuntimeException("Incorrect data");
            }
        }
        String report = makeReport(buySum, supplySum);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file");
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String makeReport(int buySum, int supplySum) {
        return new StringBuilder()
                .append(SUPPLY_OPERATION).append(SEPARATOR)
                .append(supplySum).append(System.lineSeparator())
                .append(BUY_OPERATION).append(SEPARATOR)
                .append(buySum).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR)
                .append(supplySum - buySum).append(System.lineSeparator())
                .toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file");
        }
    }
}
