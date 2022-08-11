package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA = ",";
    private int buy = 0;
    private int supply = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readFromFile(fromFileName)));
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFileName);
        List<String> strings;
        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fromFileName, e);
        }
        for (String resultStrings : strings) {
            stringBuilder.append(resultStrings).append(COMMA);
        }
        return stringBuilder.toString();
    }

    private String[] createReport(String dataFromFile) {
        int result = 0;
        String[] report = null;
        String[] split = dataFromFile.split(COMMA);
        for (int i = 0; i < split.length; i += 2) {
            if (split[i].equals("buy")) {
                buy = buy + Integer.parseInt(split[i + 1]);
            }
            if (split[i].equals("supply")) {
                supply = supply + Integer.parseInt(split[i + 1]);
            }
            result = supply - buy;
        }
        report = new String[]{"supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator() + "result," + result};
        return report;
    }

    private void writeToFile(String fileName, String[] report) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        for (String operation : report) {
            try {
                Files.write(file.toPath(), operation.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
