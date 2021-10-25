package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    public String[] readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader readerFromFile = new BufferedReader(new FileReader(file))) {
            String readedLine = readerFromFile.readLine();
            if (readedLine == null) {
                return new String[0];
            }
            while (readedLine != null) {
                stringBuilder.append(readedLine).append((System.lineSeparator()));
                readedLine = readerFromFile.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String stringReadFromFile = stringBuilder.toString();
        return stringReadFromFile.split(System.lineSeparator());
    }

    public String createReport(String[] data) {
        StringBuilder stringBuilderWithReport = new StringBuilder();
        int sumOfBuysToReport = 0;
        int sumOfSuppliesToReport = 0;
        for (String string : data) {
            String[] splittedString = string.split(",");
            if (splittedString[0].equals(BUY)) {
                sumOfBuysToReport += Integer.parseInt(splittedString[1]);
            }
            if (splittedString[0].equals(SUPPLY)) {
                sumOfSuppliesToReport += Integer.parseInt(splittedString[1]);
            }
        }
        return stringBuilderWithReport.append(SUPPLY).append(",").append(sumOfSuppliesToReport)
                .append(System.lineSeparator()).append(BUY).append(",").append(sumOfBuysToReport)
                .append(System.lineSeparator()).append(RESULT).append(",")
                .append(sumOfSuppliesToReport - sumOfBuysToReport).toString();
    }

    public void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file", e);
        }
    }
}
