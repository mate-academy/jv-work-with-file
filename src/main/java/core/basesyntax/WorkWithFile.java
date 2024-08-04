package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, getReport(fromFileName));
    }

    public static int[] readFile(String fromFileName) {
        final String split = ",";
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(split);
                if (parts[0].equals("supply")) {
                    supplySum += Integer.parseInt(parts[1]);
                } else if (parts[0].equals("buy")) {
                    buySum += Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return new int[]{supplySum, buySum};
    }

    public String getReport(String fromFileName) {
        int[] current = readFile(fromFileName);
        final int supply = current[0];
        final int buy = current[1];
        final int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}


