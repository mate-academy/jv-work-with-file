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
        if (fromFileName == null || toFileName == null
                || fromFileName.length() == 0 || toFileName.length() == 0) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        int buy = 0;
        int supply = 0;
        int result = 0;

        File writeToFile = new File(toFileName);
        File writeFromFile = new File(fromFileName);
        if (!writeFromFile.exists()) {
            return;
        }

        try {
            writeToFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(writeFromFile))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeToFile))) {

                String dataLine = reader.readLine();
                while (dataLine != null) {
                    String[] data = dataLine.split("[, ]");
                    if (dataLine.contains("buy")) {
                        buy += Integer.parseInt(data[1]);
                    } else {
                        supply += Integer.parseInt(data[1]);
                    }

                    dataLine = reader.readLine();
                }
                result = supply - buy;

                stringBuilder.append("supply")
                        .append(",")
                        .append(supply)
                        .append(System.lineSeparator());

                stringBuilder.append("buy")
                        .append(",")
                        .append(buy)
                        .append(System.lineSeparator());

                stringBuilder.append("result")
                        .append(",")
                        .append(result)
                        .append(System.lineSeparator());

                writer.write(stringBuilder.toString());
                writer.flush();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from a file", e);
        }
    }
}
