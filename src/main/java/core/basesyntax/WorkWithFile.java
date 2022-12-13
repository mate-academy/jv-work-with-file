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
    
    public void getStatistic(String fromFileName, String toFileName) {
        int[] sum = new int[3];

        try {
            File file = new File(fromFileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                String[] info = value.split(",");
                if (info[DATA_TYPE_INDEX].equals("supply")) {
                    sum[SUM_SUPPLY_INDEX] += Integer.parseInt(info[DATA_VALUE_INDEX]);
                } else {
                    sum[SUM_BUY_INDEX] += Integer.parseInt(info[DATA_VALUE_INDEX]);
                }
                value = reader.readLine();
            }
            sum[SUM_RESULT_INDEX] = sum[SUM_SUPPLY_INDEX] - sum[SUM_BUY_INDEX];
            builder.append("supply,").append(sum[SUM_SUPPLY_INDEX]).append(System.lineSeparator())
                    .append("buy,").append(sum[SUM_BUY_INDEX]).append(System.lineSeparator())
                    .append("result,").append(sum[SUM_RESULT_INDEX]);
            file = new File(toFileName);
            Files.write(file.toPath(), builder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Cannot create file", e);
        }

    }
}
