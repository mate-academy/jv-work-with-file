package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int DATA_TYPE_INDEX = 0;
    private static final int DATA_VALUE_INDEX = 1;
    private static final int SUM_SUPPLY_INDEX = 0;
    private static final int SUM_BUY_INDEX = 1;
    private static final int SUM_RESULT_INDEX = 2;
    private int[] sum = new int[3];
    private final StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            File file = new File(fromFileName);
            readFile(file);
            file = new File(toFileName);
            writeFile(file);

        } catch (IOException e) {
            throw new RuntimeException("Cannot create file", e);
        }

    }

    private int[] readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String value = reader.readLine();
        int[] readStats = new int[sum.length];
        while (value != null) {
            String[] info = value.split(",");
            if (info[DATA_TYPE_INDEX].equals("supply")) {
                readStats[SUM_SUPPLY_INDEX] += Integer.parseInt(info[DATA_VALUE_INDEX]);
            } else {
                readStats[SUM_BUY_INDEX] += Integer.parseInt(info[DATA_VALUE_INDEX]);
            }
            value = reader.readLine();
        }
        sum = readStats;
        return sum;
    }

    private String generateStat(int[] incoming) {
        incoming[SUM_RESULT_INDEX] = incoming[SUM_SUPPLY_INDEX] - incoming[SUM_BUY_INDEX];
        builder.append("supply,").append(incoming[SUM_SUPPLY_INDEX]).append(System.lineSeparator())
                .append("buy,").append(incoming[SUM_BUY_INDEX]).append(System.lineSeparator())
                .append("result,").append(incoming[SUM_RESULT_INDEX]);
        String result = builder.toString();
        builder.setLength(0);
        return result;
    }

    private int[] writeFile(File fileToWrite) {
        try {
            Files.write(fileToWrite.toPath(),generateStat(sum).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sum = new int[3];
        return sum;
    }
}
