package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private String[] readFromFile(String fromFileName) {
        try {
            File fromFile = new File(fromFileName);
            return Files.readAllLines(fromFile.toPath()).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName, String[] strings) {
        try {
            File toFile = new File(toFileName);
            toFile.createNewFile();
            String[] outputString = outputString(strings);
            for (String string: outputString) {
                Files.writeString(toFile.toPath(),string, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    private String[] outputString(String[] strings) {
        int buy = 0;
        int supply = 0;
        for (String string: strings) {
            if (string.split(",")[0].equals("buy")) {
                buy = buy + Integer.parseInt(string.split(",")[1]);
            }
            if (string.split(",")[0].equals("supply")) {
                supply = supply + Integer.parseInt(string.split(",")[1]);
            }
        }
        int result = supply - buy;
        return new String[] {"supply," + supply + System.lineSeparator(),
                "buy," + buy + System.lineSeparator(),
                "result," + result};
    }
}
