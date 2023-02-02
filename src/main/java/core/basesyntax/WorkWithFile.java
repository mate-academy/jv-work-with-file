package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        int supply = 0;
        int buy = 0;

        String[] lineValue;
        String nextString;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
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
            StringBuilder report = new StringBuilder("supply,");
            report.append(supply);
            report.append(System.lineSeparator());
            report.append("buy,");
            report.append(buy);
            report.append(System.lineSeparator());
            report.append("result,");
            report.append(supply - buy);

            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
