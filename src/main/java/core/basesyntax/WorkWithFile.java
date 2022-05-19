package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_PARAMETER = 0;
    private static final int INDEX_VALUE = 1;
    private static final String STRING_SUPPLY = "supply";
    private static final String STRING_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineList = line.split(",");
                switch (lineList[INDEX_PARAMETER]) {
                    case (STRING_SUPPLY): {
                        supply += Integer.parseInt(lineList[INDEX_VALUE]);
                        break;
                    }
                    case (STRING_BUY): {
                        buy += Integer.parseInt(lineList[INDEX_VALUE]);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        StringBuilder report = new StringBuilder();
        report.append(STRING_SUPPLY)
                .append(",")
                .append(supply)
                .append(System.lineSeparator());
        report.append(STRING_BUY)
                .append(",")
                .append(buy)
                .append(System.lineSeparator());
        report.append("result")
                .append(",")
                .append(supply - buy)
                .append(System.lineSeparator());
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(report.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
