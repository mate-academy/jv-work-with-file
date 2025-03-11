package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
    }

    private String[] readeFile(String fromFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            int value = reader.read();
            StringBuilder builder = new StringBuilder();
            while (value != -1) {
                builder.append((char) (value));
                value = reader.read();
            }
            System.out.println(builder);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return null;
    }

    private String createReport(String[] data) {
        int supply = 0;
        int buy = 0;
        for (String currentData : data) {
            if (currentData.split(",")[0].equals("supply")) {
                supply += Integer.parseInt(currentData.split(",")[1]);
            } else if (currentData.split(",")[1].equals("buy")) {
                buy += Integer.parseInt(currentData.split(",")[2]);
            }
        }
        return null;
    }

    private void writeToFile(String report, String toFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
