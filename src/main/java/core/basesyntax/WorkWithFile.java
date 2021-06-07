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
        int supply = 0;
        int buy = 0;
        int index;
        try (BufferedReader buffered = new BufferedReader(new FileReader(fromFileName))) {
            for (String line = buffered.readLine(); line != null; line = buffered.readLine()) {
                index = line.indexOf(DELIMITER);
                if (line.substring(0, index).equals("supply")) {
                    supply += Integer.parseInt(line.substring(index + 1));
                } else if (line.substring(0, index).equals("buy")) {
                    buy += Integer.parseInt(line.substring((index + 1)));
                }
            }

        } catch (IOException ioe) {
            throw new RuntimeException("The file cannot be read", ioe);
        }
        StringBuilder builder = new StringBuilder("supply").append(DELIMITER)
                .append(supply).append(SEPARATOR)
                .append("buy").append(DELIMITER).append(buy).append(SEPARATOR)
                .append("result").append(DELIMITER).append(supply - buy);
        writeToFile(builder.toString(), toFileName);
    }

    private void writeToFile(String writeThis, String toFileName) {
        try (BufferedWriter writeInFile = new BufferedWriter(new FileWriter(toFileName))) {
            writeInFile.write(writeThis);
        } catch (IOException ioe) {
            throw new RuntimeException("Can't write to file!", ioe);
        }
    }
}
