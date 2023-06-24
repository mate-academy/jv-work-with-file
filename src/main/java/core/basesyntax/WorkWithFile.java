package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    // Constants for the operation types
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    // Indices for the key and value in the CSV line
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    // Method to get statistics from the input file and save it to the output file
    public void getStatistic(String fromFileName, String toFileName) {
        // Read data from the input file
        ArrayList<String> data = readData(fromFileName);

        // Calculate the statistics from the data
        int[] statistics = calculateStatistics(data);

        // Create the report based on the statistics
        String report = createReport(statistics);

        // Write the report to the output file
        writeToFile(report, toFileName);
    }

    // Method to read data from the input file
    private ArrayList<String> readData(String fromFileName) {
        // Create a File object from the input file name
        File fromFile = new File(fromFileName);

        // ArrayList to store the read data
        ArrayList<String> data = new ArrayList<>();

        String nextString;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            // Read each line from the file
            nextString = reader.readLine();
            while (nextString != null) {
                data.add(nextString);
                nextString = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    // Method to calculate statistics from the data
    private int[] calculateStatistics(ArrayList<String> data) {
        // Variables to store the accumulated values for supply and buy
        int supply = 0;
        int buy = 0;

        // Iterate through each line of data
        for (String line : data) {
            // Split the line by comma to separate the key and value
            String[] lineValue = line.split(",");

            // Check the operation type and update the corresponding value
            if (lineValue[KEY_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(lineValue[VALUE_INDEX].trim());
            } else if (lineValue[KEY_INDEX].equals(BUY)) {
                buy += Integer.parseInt(lineValue[VALUE_INDEX].trim());
            }
        }

        // Create an array with the calculated statistics
        int[] statistics = new int[]{supply, buy};
        return statistics;
    }

    // Method to create the report based on the statistics
    private String createReport(int[] statistics) {
        // Retrieve the supply and buy values from the statistics array
        int supply = statistics[0];
        int buy = statistics[1];

        // Build the report using a StringBuilder
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supply).append(System.lineSeparator());
        report.append("buy,").append(buy).append(System.lineSeparator());
        report.append("result,").append(supply - buy);

        return report.toString();
    }

    // Method to write the report to the output file
    private void writeToFile(String report, String toFileName) {
        // Create a File object from the output file name
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            // Write the report to the output file
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
