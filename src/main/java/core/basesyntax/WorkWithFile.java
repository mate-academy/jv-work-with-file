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
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String readedLine = reader.readLine();
            if (readedLine == null) {
                return new String[0];
            }
            while (readedLine != null) {
                stringBuilder.append(readedLine).append((System.lineSeparator()));
                readedLine = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String str = stringBuilder.toString();
        return str.split(System.lineSeparator());
    }

    public String createReport(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        int sumOfBuys = 0;
        int sumOfSupplies = 0;
        for (String string : data) {
            String[] splittedString = string.split(",");
            if (splittedString[0].equals(BUY)) {
                sumOfBuys += Integer.parseInt(splittedString[1]);
            }
            if (splittedString[0].equals(SUPPLY)) {
                sumOfSupplies += Integer.parseInt(splittedString[1]);
            }
        }
        return stringBuilder.append(SUPPLY).append(",").append(sumOfSupplies)
                .append(System.lineSeparator()).append(BUY).append(",").append(sumOfBuys)
                .append(System.lineSeparator()).append(RESULT).append(",")
                .append(sumOfSupplies - sumOfBuys).toString();
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
