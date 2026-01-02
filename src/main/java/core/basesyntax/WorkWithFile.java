package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final int STRING_NAME = 0;
    private static final int NUMBERS = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
        return sb.toString();
    }

    private String createReport(String data) {
        int supply = 0;
        int buy = 0;
        String[] lines = data.split(System.lineSeparator());

        for (String line : lines) {
            if (line.isEmpty()) continue;
            String[] parts = line.split(",");
            int amount = Integer.parseInt(parts[NUMBERS]);
            if (parts[STRING_NAME].equals("supply")) {
                supply += amount;
            } else if (parts[STRING_NAME].equals("buy")) {
                buy += amount;
            }
        }

        int result = supply - buy;
        return "supply," + supply + System.lineSeparator() +
                "buy," + buy + System.lineSeparator() +
                "result," + result;
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}