package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int ACTION_INDEX = 0;
    static final int VALUE_INDEX = 1;
    static final String ACTION_SUPPLY = "supply";
    static final String ACTION_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            String[] array;
            int supply = 0;
            int buy = 0;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();

            while (value != null) {
                array = value.split(",");
                if (array[ACTION_INDEX].equals(ACTION_SUPPLY)) {
                    supply += Integer.parseInt(array[VALUE_INDEX]);
                }
                if (array[ACTION_INDEX].equals(ACTION_BUY)) {
                    buy += Integer.parseInt(array[VALUE_INDEX]);
                }
                value = reader.readLine();
            }
            builder.append(ACTION_SUPPLY).append(",").append(supply).append(System.lineSeparator())
                    .append(ACTION_BUY).append(",").append(buy).append(System.lineSeparator())
                    .append("result").append(',').append(supply - buy);
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
