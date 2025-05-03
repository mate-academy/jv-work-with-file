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
        StringBuilder inDatas = inputFromFile(fromFileName);
        String outDatas = calculateStatistic(inDatas);
        outputToFile(toFileName, outDatas);
    }

    private StringBuilder inputFromFile(String fromFileName) {
        StringBuilder inDatas = new StringBuilder();
        try (BufferedReader fromFile = new BufferedReader(new FileReader(fromFileName))) {
            while (fromFile.ready()) {
                inDatas.append(fromFile.readLine())
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return inDatas;
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
        String outDatas = new String();
        outDatas = outDatas.concat(keys[OPERATION_TYPE]).concat(DELIMITER)
                .concat(String.valueOf(values[OPERATION_TYPE])).concat(System.lineSeparator());
        outDatas = outDatas.concat(keys[AMOUNT]).concat(DELIMITER)
                .concat(String.valueOf(values[AMOUNT])).concat(System.lineSeparator());
        outDatas = outDatas.concat(RESULT_OPERATION).concat(DELIMITER)
                .concat(String.valueOf(values[OPERATION_TYPE] - values[AMOUNT]))
                .concat(System.lineSeparator());
        clear();
        return outDatas;
    }

    private void clear() {
        keys = new String[FIELDS_SIZE];
        values = new Integer[FIELDS_SIZE];
    }
}
