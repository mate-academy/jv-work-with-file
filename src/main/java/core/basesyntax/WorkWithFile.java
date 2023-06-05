package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private final StringBuilder array = new StringBuilder();
    private boolean dataWritten = false;

    public void getStatistic(String fromFileName, String toFileName) {
        createReport(fromFileName);
        writeToFile(toFileName);
    }

    private void createReport(String fileName) {
        File file = new File(fileName);
        int supplyValue = 0;
        int buyValue = 0;
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            String text = String.join(System.lineSeparator(), strings);
            String validText = text.replace(System.lineSeparator(), ",");
            String[] value = validText.split(",");
            for (int i = 0; i < value.length; i++) {
                if (value[i].equals("supply")) {
                    supplyValue += Integer.parseInt(value[i + 1]);
                }
                if (value[i].equals("buy")) {
                    buyValue += Integer.parseInt(value[i + 1]);
                }
            }
            int result = supplyValue - buyValue;
            array
                    .append(getBuilder("supply", supplyValue))
                    .append(getBuilder("buy", buyValue))
                    .append("result,")
                    .append(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private StringBuilder getBuilder(String string, int value) {
        return new StringBuilder()
                .append(string)
                .append(",")
                .append(value)
                .append(System.lineSeparator());
    }

    private void writeToFile(String fileName) {
        File file = new File(fileName);
        if (!dataWritten) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(array.toString());
                dataWritten = true;
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}

