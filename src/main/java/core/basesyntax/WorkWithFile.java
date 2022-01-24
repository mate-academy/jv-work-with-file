package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String fromFileText = readFromFile(fromFileName);
            String report = createReport(fromFileText);
            writeToFile(report, toFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFromFile(String fromFileName) throws IOException {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("File error", e);
        }
    }

    public String createReport(String contentFromFile) {
        int supplySum = 0;
        int buySum = 0;
        final int stringPart = 0;
        final int intPart = 1;
        final String supply = "supply";
        final String buy = "buy";
        String[] split = contentFromFile.split(System.lineSeparator());
        for (String line:
                split) {
            if (line.equals("")) {
                continue;
            }
            if (line.split(",")[stringPart].equals(supply)) {
                supplySum += Integer.parseInt(line.split(",")[intPart]);
            }
            if (line.split(",")[stringPart].equals(buy)) {
                buySum += Integer.parseInt(line.split(",")[intPart]);
            }
        }
        return "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum);
    }

    public void writeToFile(String report, String toFileName) throws IOException {
        File file = new File(toFileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(report);
        } catch (IOException e) {
            throw new IOException("Write to file problem", e);
        }
    }
}
