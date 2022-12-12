package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    private static String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder data = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                data.append(value).append(',');
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot open file", e);
        } catch (IOException e) {
            throw new RuntimeException("IO exception", e);
        } catch (NullPointerException e) {
            throw new RuntimeException("Null pointer exception");
        }
        return data.toString();
    }

    private void writeToFile(String report, String fileName) {
        File file = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("IO exception", e);
        }
    }

    private static String createReport(String data) {

        int supply = 0;
        int buy = 0;
        int result;

        StringBuilder report = new StringBuilder();
        String[] split = data.split(",");

        for (int i = 0; i < split.length; i += 2) {
            if (split[i] != null) {
                if (split[i].equals("supply")) {
                    supply += Integer.parseInt(split[i + 1]);
                } else if (split[i].equals("buy")) {
                    buy += Integer.parseInt(split[i + 1]);
                }
            }
        }

        result = supply - buy;

        report.append("supply,").append(supply).append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator()).append("result,").append(result);

        return report.toString();
    }
}
