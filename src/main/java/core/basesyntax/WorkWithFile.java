package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] readData = readData(fromFileName);
        String[] report = generateReport(readData);
        writeReportToFile(report, toFileName);
    }

    private String[] readData(String fileName) {
        Path pathOfFile = Path.of(fileName);
        try {
            List<String> lines = Files.readAllLines(pathOfFile);
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file : " + fileName, e);
        }
    }

    private String[] generateReport(String[] statisticData) {
        int supply = 0;
        int buy = 0;
        int result;
        int valueOfOperation;
        String[] separatedRow;
        String operation;

        for (String row : statisticData) {
            separatedRow = row.split(",");
            operation = separatedRow[0];
            valueOfOperation = Integer.parseInt(separatedRow[1]);

            if (operation.equals("supply")) {
                supply += valueOfOperation;
            } else {
                buy += valueOfOperation;
            }
        }

        result = supply - buy;

        return new String[] {"supply," + supply, "buy," + buy,"result," + result};
    }

    private void writeReportToFile(String[] report, String fileName) {
        File file = new File(fileName);
        Path pathOfFile = file.toPath();

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file: " + fileName, e);
        }

        StringBuilder formattedData = new StringBuilder();
        for (String row : report) {
            formattedData.append(row).append(System.lineSeparator());
        }

        try {
            Files.write(pathOfFile, formattedData.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
