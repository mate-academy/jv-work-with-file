package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_PART_ARRAY = 0;
    private static final int COUNT_PART_ARRAY = 1;
    private static final String SUPPLY_CONSTANT = "supply";

    public void getStatistic(String fromFileName, String toFileName) {

        File initialFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(initialFile))) {
            String bufferValue = bufferedReader.readLine();
            String name = "";
            String count = "";
            String resultText = "";

            while (bufferValue != null) {
                name = bufferValue.split(",")[NAME_PART_ARRAY];
                count = bufferValue.split(",")[COUNT_PART_ARRAY];

                if (name.equals(SUPPLY_CONSTANT)) {
                    supply += Integer.parseInt(count);
                } else {
                    buy += Integer.parseInt(count);
                }

                bufferValue = bufferedReader.readLine();
            }

            resultText = createResultString(supply, buy);
            writeToFile(toFileName, resultText);
        } catch (IOException e) {
            throw new RuntimeException("Something wrong with file", e);
        }
    }

    private String createResultString(int supply, int buy) {
        StringBuilder writerStringBuilder = new StringBuilder();
        final int result = supply - buy;
        writerStringBuilder.append("supply,").append(supply).append("\n")
                .append("buy,").append(buy).append("\n")
                .append("result,").append(result).append("\n");

        return writerStringBuilder.toString();
    }

    private void writeToFile(String fileName, String text) {
        File newFile = new File(fileName);

        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(newFile))) {
            bufferWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to " + fileName, e);
        }
    }
}
