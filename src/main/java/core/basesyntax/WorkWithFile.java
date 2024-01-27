package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    static final String SEPARATOR = ",";
    static final int OPERATION_INDEX = 0;
    static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readData = readData(fromFileName);
        String report = generateReport(readData);
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

    private String generateReport(String[] statisticData) {
        int supply = 0;
        int buy = 0;

        for (String row : statisticData) {
            String[] separatedRow = row.split(SEPARATOR);
            String operation = separatedRow[OPERATION_INDEX];
            int valueOfOperation = Integer.parseInt(separatedRow[VALUE_INDEX]);

            if (operation.equals("supply")) {
                supply += valueOfOperation;
            } else {
                buy += valueOfOperation;
            }
        }

        int result = supply - buy;
        String[] preFormat = new String[] {"supply," + supply, "buy," + buy,"result," + result};
        StringBuilder formattedReport = new StringBuilder();

        for (String row : preFormat) {
            formattedReport.append(row).append(System.lineSeparator());
        }

        return formattedReport.toString();
    }

    private void writeReportToFile(String report, String fileName) {
        try {
            Files.write(Path.of(fileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
