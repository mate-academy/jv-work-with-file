package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String RESULT_WORD = "result";
    private static final String SEPARATOR_CHAR = ",";
    private static final int VALUE_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value;
            while ((value = reader.readLine()) != null) {
                stringBuilder.append(value)
                        .append(System.lineSeparator());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private String createReport(String dataFromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        int buySum = 0;
        int supplySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new StringReader(dataFromFile))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String [] arrayFile = value.split(SEPARATOR_CHAR);
                if (arrayFile[VALUE_INDEX].equals(BUY_WORD)) {
                    buySum += Integer.parseInt(arrayFile[NUMBER_INDEX]);
                } else {
                    supplySum += Integer.parseInt(arrayFile[NUMBER_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't create file" + dataFromFile, e);
        }
        return stringBuilder.append(SUPPLY_WORD).append(SEPARATOR_CHAR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY_WORD).append(SEPARATOR_CHAR).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT_WORD).append(SEPARATOR_CHAR).append(supplySum - buySum)
                .toString();
    }

    private void writeToFile(String input, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(input);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to this file", e);
        }
    }
}
