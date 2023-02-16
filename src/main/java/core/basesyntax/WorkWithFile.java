package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        // exit immediately if the target file already exists
        File file = new File(toFileName);
        if (file.exists()) {
            return;
        }
        String dataFromFile = readFromFile(fromFileName);
        String report = generateReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // I read data symbol by symbol to preserve the original formatting
            int symbol = reader.read();
            while (symbol != -1) {
                builder.append((char) symbol);
                symbol = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName, e);
        }

        return builder.toString();
    }

    private String generateReport(String data) {
        int supply = 0;
        int buy = 0;
        String[] entries = data.split("\r\n?|\n"); // split by line separator

        for (String entry: entries) {
            String[] values = entry.split(",");
            switch (values[OPERATION_INDEX]) {
                case "supply":
                    supply += Integer.parseInt(values[AMOUNT_INDEX]);
                    break;
                case "buy":
                    buy += Integer.parseInt(values[AMOUNT_INDEX]);
                    break;
                default:
                    break;
            }
        }

        StringBuilder builder = new StringBuilder();
        String separator = System.lineSeparator();
        builder.append("supply," + supply + separator)
                .append("buy," + buy + separator)
                .append("result," + (supply - buy) + separator);

        return builder.toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
