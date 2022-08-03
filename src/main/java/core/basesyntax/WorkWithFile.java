package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_COLUMN = 0;
    private static final int AMOUNT_COLUMN = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String[] strings = readFromFile(fromFileName);
        for (String string : strings) {
            String[] split = string.split(REGEX);
            if (split[OPERATION_COLUMN].equals(SUPPLY)) {
                supply += Integer.parseInt(split[AMOUNT_COLUMN]);
            }
            if (split[OPERATION_COLUMN].equals(BUY)) {
                buy += Integer.parseInt(split[AMOUNT_COLUMN]);
            }
        }
        writeToFile(toFileName, supply, buy);
    }

    private String[] readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> list;
        try {
            list = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return list.toArray(new String[list.size()]);
    }

    private void writeToFile(String toFileName, int supply, int buy) {
        File file = new File(toFileName);
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder()
                .append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
