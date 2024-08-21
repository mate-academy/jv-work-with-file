package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
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
        String report;
        String[] splitDataFromFile = dataFromFile.split("\n");
        for (String element : splitDataFromFile) {
            String[] splitElement = element.split(",");
            for (int i = 0; i < splitElement.length; i += 2) {
                if (splitElement[i].equals("supply")) {
                    supply += Integer.parseInt(splitElement[1]);
                }
                if (splitElement[i].equals("buy")) {
                    buy += Integer.parseInt(splitElement[1]);
                }
            }
            result = supply - buy;
        }
        report = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
        return report;
    }

    public void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
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
