package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder strResult = new StringBuilder();
        List<String> listData = readCsv(fromFileName);
        int supplyResult = 0;
        int buyResult = 0;
        int sumResult;
        for (String listDatum : listData) {
            String[] str = listDatum.split(COMMA);
            if (str[INDEX_ZERO].equals(SUPPLY)) {
                supplyResult += Integer.parseInt(str[INDEX_ONE]);
            }
            if (str[INDEX_ZERO].equals(BUY)) {
                buyResult += Integer.parseInt(str[INDEX_ONE]);
            }
        }
        sumResult = supplyResult - buyResult;
        strResult.append(SUPPLY + COMMA).append(supplyResult)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(buyResult).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(sumResult);
        writeCsv(toFileName, strResult.toString());
    }

    private List<String> readCsv(String fromFileName) {
        File file = new File(fromFileName);
        List<String> strings;
        try {
            strings = Files.readAllLines(file.toPath());

        } catch (IOException e) {
            throw new RuntimeException("Can,t read file", e);
        }
        return strings;
    }

    private void writeCsv(String toFileName, String data) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}