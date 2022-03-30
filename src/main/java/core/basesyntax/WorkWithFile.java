package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private StringBuilder temp = new StringBuilder();
    private StringBuilder report = new StringBuilder();
    private String file = null;

    public void getStatistic(String fromFileName, String toFileName) {
        file = fromFileName;
        readFile();
        try {
            Files.write(Path.of(toFileName), prepareReport().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to file : " + file, e);
        }
    }

    private void readFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                temp.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the file : " + file, e);
        }
    }

    private String prepareReport() {
        String[] reportData = temp.toString().split("\\W+");
        String value;
        int supply = 0;
        int buy = 0;

        for (int i = 0; i < reportData.length; i = i + 2) {
            value = reportData[i + 1];
            if (reportData[i].equals("supply")) {
                supply += Integer.parseInt(value);
            } else {
                buy += Integer.parseInt(value);
            }
        }

        int result = supply - buy;
        report.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        return report.toString();
    }
}


