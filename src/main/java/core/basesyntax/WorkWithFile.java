package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int INDEX_FOR_ACTION = 0;
    private static final int INDEX_FOR_AMOUNT = 1;
    private static final String ACTION_NAME = "supply";

    private String[] readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file" + fileName, e);
        }
        return stringBuilder.toString().trim().split(" ");
    }

    private byte[] createReport(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        int supply = 0;
        int purchases = 0;
        for (String datum : data) {
            String[] information = datum.split(",");
            if (information[INDEX_FOR_ACTION].equals(ACTION_NAME)) {
                supply += Integer.parseInt(information[INDEX_FOR_AMOUNT]);
            } else {
                purchases += Integer.parseInt(information[INDEX_FOR_AMOUNT]);
            }
        }
        String report = stringBuilder.append("supply,").append(supply)
                        .append(System.lineSeparator())
                        .append("buy,").append(purchases).append(System.lineSeparator())
                        .append("result,").append(supply - purchases).toString();
        return report.getBytes();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            Files.write(Path.of(toFileName), createReport(readFile(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to file" + toFileName, e);
        }
    }
}
