package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            String valueBuilder = builder.toString();
            String[] counter = valueBuilder.split(System.lineSeparator());
            int buy = 0;
            int supply = 0;
            for (String line: counter) {
                String[] splitData = line.split("\\W+");
                if (splitData[FIRST_COLUMN].equals("supply")) {
                    supply += Integer.parseInt(splitData[SECOND_COLUMN]);
                } else {
                    buy += Integer.parseInt(splitData[SECOND_COLUMN]);
                }
            }
            int result = supply - buy;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(result).append(System.lineSeparator());
            File report = new File(toFileName);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(report))) {
                bufferedWriter.write(stringBuilder.toString());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
