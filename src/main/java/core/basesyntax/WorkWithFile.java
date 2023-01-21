package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int WORD_INDEX = 0;
    static final int VALUE_INDEX = 1;

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
                if (array[WORD_INDEX].equals("supply")) {
                    supply += Integer.parseInt(array[VALUE_INDEX]);
                }
                if (array[WORD_INDEX].equals("buy")) {
                    buy += Integer.parseInt(array[VALUE_INDEX]);
                }
                value = reader.readLine();
            }
            builder.append("supply").append(",").append(supply).append(System.lineSeparator())
                    .append("buy").append(",").append(buy).append(System.lineSeparator())
                    .append("result").append(',').append(supply - buy);
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
