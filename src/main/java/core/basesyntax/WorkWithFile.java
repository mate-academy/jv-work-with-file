package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = readFromFile(fromFileName);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder fileReader = new StringBuilder();
        int supply = 0;
        int buy = 0;
        try {
            List<String> fileToRead = Files.readAllLines(Paths.get(fromFileName));
            for (int i = 0; i < fileToRead.size(); i++) {
                String[] readerArr = fileToRead.get(i).split(COMMA);
                if (readerArr[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(readerArr[1]);
                } else {
                    buy += Integer.parseInt(readerArr[1]);
                }
            }
            fileReader.append(SUPPLY).append(COMMA).append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy)
                .append(System.lineSeparator());

            return fileReader.toString();

        } catch (IOException e) {
            throw new RuntimeException("Can't read to file", e);
        }
    }

    private void writeToFile(String fromFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fromFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}

