package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static int[] getReportFromFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            int[] report = new int[2];

            while (line != null) {
                String[] splitLine = line.split(",");
                if (splitLine[0].equals("supply")) {
                    report[0] += Integer.parseInt(splitLine[1]);
                } else {
                    report[1] += Integer.parseInt(splitLine[1]);
                }
                line = reader.readLine();
            }
            return report;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fileName + " not found", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred", e);
        }
    }

    public static void writeReportToFile(String fileName, String data) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fileName + " not found", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred", e);
        }
    }

    public static void getStatistic(String fromFileName, String toFileName) {
        int[] report = getReportFromFile(fromFileName);
        String resultString = "supply," + report[0]
                + System.lineSeparator() + "buy," + report[1]
                + System.lineSeparator() + "result," + (report[0] - report[1]);
        writeReportToFile(toFileName, resultString);
    }
}
