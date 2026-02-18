package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            int value = reader.read();

            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        String[] lines = stringBuilder.toString().split(System.lineSeparator());
        for (String line : lines) {
            String [] contentArray = line.split(",");
            if (contentArray[0].equals("supply")) {
                supply += Integer.parseInt(contentArray[1]);
            } else if (contentArray[0].equals("buy")) {
                buy += Integer.parseInt(contentArray[1]);
            }
        }
        result = supply - buy;
        StringBuilder stringBuilderToFile = new StringBuilder();
        stringBuilderToFile.append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator())
                .append("result,").append(result);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringBuilderToFile.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
