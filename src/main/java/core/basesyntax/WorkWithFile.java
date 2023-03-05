package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readData(fromFileName));
        writeToFile(report, toFileName);
    }

    private int[] readData(String fromFileName) {
        File fromFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        String[] lineValue;
        String nextString;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            nextString = reader.readLine();
            while (nextString != null) {
                lineValue = nextString.split(",");
                if (lineValue[0].equals("supply")) {
                    supply += Integer.parseInt(lineValue[1]);
                } else if (lineValue[0].equals("buy")) {
                    buy += Integer.parseInt(lineValue[1]);
                }
                nextString = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[] data = new int[]{supply, buy};
        return data;
    }

    private String createReport(int[] data) {
        StringBuilder report = new StringBuilder("supply,");
        int supply = data[0];
        int buy = data[1];
        report.append(supply);
        report.append(System.lineSeparator());
        report.append("buy,");
        report.append(buy);
        report.append(System.lineSeparator());
        report.append("result,");
        report.append(supply - buy);
        return report.toString();
    }

    private void writeToFile(String report, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
