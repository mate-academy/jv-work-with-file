package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] stats = readData(fromFileName);
        String report = createReport(stats[0], stats[1]);
        writeData(toFileName, report);
    }

    private int[] readData(String fileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (operation.equals("supply")) {
                    supply += amount;
                } else if (operation.equals("buy")) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
        return new int[]{supply, buy};
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result + System.lineSeparator();
    }

    private void writeData(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}
