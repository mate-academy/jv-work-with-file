package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int FIELDS_SIZE = 2;
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";
    private String[] keys = new String[FIELDS_SIZE];
    private Integer[] values = new Integer[FIELDS_SIZE];

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder inDatas = new StringBuilder();
        inputFromFile(fromFileName, inDatas);
        String outDatas = calculateStatistic(inDatas);
        outputToFile(toFileName, outDatas);
    }

    private void inputFromFile(String fromFileName, StringBuilder datas) {
        try (BufferedReader fromFile = new BufferedReader(new FileReader(fromFileName))) {
            while (fromFile.ready()) {
                datas.append(fromFile.readLine())
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
    }

    private void outputToFile(String toFileName, String datas) {
        try (BufferedWriter toFile = new BufferedWriter(new FileWriter(toFileName))) {
            toFile.write(datas);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private String calculateStatistic(StringBuilder datas) {
        String[] lines = datas.toString().split(System.lineSeparator());
        for (String line : lines) {
            String[] entries = line.split(DELIMITER);
            var position = (entries[OPERATION_TYPE].trim().equals(BUY_OPERATION)) ? AMOUNT
                    : OPERATION_TYPE;
            if (keys[position] == null) {
                keys[position] = entries[OPERATION_TYPE].trim();
                values[position] = Integer.parseInt(entries[AMOUNT].trim());
            } else {
                values[position] += Integer.parseInt(entries[AMOUNT].trim());
            }
        }
        StringBuilder outDatas = new StringBuilder();
        outDatas.append(keys[OPERATION_TYPE]).append(DELIMITER).append(values[OPERATION_TYPE])
                .append(System.lineSeparator());
        outDatas.append(keys[AMOUNT]).append(DELIMITER).append(values[AMOUNT])
                .append(System.lineSeparator());
        outDatas.append(RESULT_OPERATION).append(DELIMITER)
                .append(values[OPERATION_TYPE] - values[AMOUNT]).append(System.lineSeparator());
        clear();
        return outDatas.toString();
    }

    private void clear() {
        keys = new String[FIELDS_SIZE];
        values = new Integer[FIELDS_SIZE];
    }
}
