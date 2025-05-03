package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fileName) {
        File file = new File(fileName);
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            String text = String.join(System.lineSeparator(), strings);
            String validText = text.replace(System.lineSeparator(), ",");
            return validText.split(",");
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
    }

    private String createReport(String[] validArray) {
        int supplyValue = 0;
        int buyValue = 0;
        for (int i = 0; i < validArray.length; i++) {
            if (validArray[i].equals("supply")) {
                supplyValue += Integer.parseInt(validArray[i + 1]);
            }
            if (validArray[i].equals("buy")) {
                buyValue += Integer.parseInt(validArray[i + 1]);
            }
        }
        int result = supplyValue - buyValue;
        return getBuilder("supply", supplyValue)
                .append(getBuilder("buy", buyValue))
                .append(getBuilder("result", result))
                .toString();
    }

    private StringBuilder getBuilder(String string, int value) {
        return new StringBuilder()
                .append(string)
                .append(",")
                .append(value)
                .append(System.lineSeparator());
    }

    private void writeToFile(String data, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}
