package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file, ", e);
        }
    }

    private String createReport(String data) {
        String[] line = data.split("[,\r\n]+");
        int supply = 0;
        int buy = 0;

        for (int i = 0; i < line.length - 1; i += 2) {
            if (line[i].equals(SUPPLY)) {
                supply += Integer.parseInt(line[i + 1]);
            }
            if (line[i].equals(BUY)) {
                buy += Integer.parseInt(line[i + 1]);
            }
        }

        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file, ", e);
        }

        return stringBuilder.toString();
    }
}
