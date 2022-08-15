package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFromFile(fromFileName);
        String report = createReport(fileData);
        writeToFile(toFileName, report);
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

    private String createReport(String dataFromFile) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        String report;
        String[] splittedFileData = dataFromFile.split(COMMA);
        for (int i = 0; i < splittedFileData.length; i += 2) {
            if (splittedFileData[i].equals("buy")) {
                buy = buy + Integer.parseInt(splittedFileData[i + 1]);
            }
            if (splittedFileData[i].equals("supply")) {
                supply = supply + Integer.parseInt(splittedFileData[i + 1]);
            }
            result = supply - buy;
        }
        report = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator() + "result," + result;
        return report;
    }

    private void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file " + fileName, e);
        }
        try {
            Files.write(file.toPath(), report.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}
