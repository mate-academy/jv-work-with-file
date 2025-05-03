package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int OPERATION_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fileName) {
        String dataFromFile;
        try {
            dataFromFile = Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fileName, e);
        }
        return dataFromFile;
    }

    public String createReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        String[] splitDataFromFile = dataFromFile.split(System.lineSeparator());
        for (String element : splitDataFromFile) {
            String[] splitElement = element.split(SEPARATOR);
            for (int i = 0; i < splitElement.length; i += 2) {
                if (splitElement[i].equals(SUPPLY)) {
                    supply += Integer.parseInt(splitElement[OPERATION_VALUE_INDEX]);
                }
                if (splitElement[i].equals(BUY)) {
                    buy += Integer.parseInt(splitElement[OPERATION_VALUE_INDEX]);
                }
            }
            result = supply - buy;
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(SEPARATOR).append(supply).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buy).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(result);
        return reportBuilder.toString();
    }

    public void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can't create new file", e);
            }
            try {
                Files.write(Path.of(fileName), report.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
