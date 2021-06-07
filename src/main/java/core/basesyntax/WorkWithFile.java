package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String toFile = "";
        StringBuilder builder = new StringBuilder();
        try (BufferedReader buffered = new BufferedReader(new FileReader(fromFileName))) {
            int supply = 0;
            int buy = 0;
            for (String line = buffered.readLine(); line != null; line = buffered.readLine()) {
                if (line.substring(0, line.indexOf(DELIMITER)).equals("supply")) {
                    supply += Integer.parseInt(line.substring(line.indexOf(DELIMITER) + 1));
                } else if (line.substring(0, line.indexOf(DELIMITER)).equals("buy")) {
                    buy += Integer.parseInt(line.substring((line.indexOf(DELIMITER) + 1)));
                }
            }
            builder.append("supply").append(DELIMITER).append(supply).append(SEPARATOR)
                    .append("buy").append(DELIMITER).append(buy).append(SEPARATOR)
                    .append("result" + DELIMITER + (supply - buy));
            toFile = builder.toString();
            writeToFile(toFile, toFileName);
        } catch (IOException ioe) {
            throw new RuntimeException("The file cannot be read", ioe);
        }
    }

    private void writeToFile(String writeThis, String toFileName) {
        try (BufferedWriter writeInFile = new BufferedWriter(new FileWriter(toFileName))) {
            writeInFile.write(writeThis);
        } catch (IOException ioe) {
            throw new RuntimeException("Can't write to file!", ioe);
        }
    }
}
