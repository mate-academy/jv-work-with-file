package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_TYPE = 0;
    private static final int BUY_TYPE = 1;
    private static final int OPERATION_TYPE_SUPPLY = 0;
    private static final int OPERATION_TYPE_BUY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            return data.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
    }

    private String createReport(String data) {
        int supply = 0;
        int buy = 0;
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            int operationType = "supply".equals(parts[0]) ? OPERATION_TYPE_SUPPLY
                                                          : OPERATION_TYPE_BUY;
            int amount = Integer.parseInt(parts[1]);
            if (operationType == OPERATION_TYPE_SUPPLY) {
                supply += amount;
            } else if (operationType == OPERATION_TYPE_BUY) {
                buy += amount;
            }
        }
        int result = supply - buy;
        return "supply," + supply + "\nbuy," + buy + "\nresult," + result + "\n";
    }

    private void writeToFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        }
    }
}
