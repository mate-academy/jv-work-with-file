package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {

        List<String> readedData = readData(fromFileName);

        int[] data = sortData(readedData); //sorts data between supply and buy

        writeData(toFileName, data);
    }

    private List<String> readData(String fromFileName) {
        File fromFile = new File(fromFileName);
        try {
            return Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private int[] sortData(List<String> readedData) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < readedData.size(); i++) {
            String[] strings = readedData.get(i).split(DELIMITER);
            if (strings[ZERO_INDEX].equals("supply")) {
                supply += Integer.parseInt(strings[FIRST_INDEX]);
            } else {
                buy += Integer.parseInt(strings[FIRST_INDEX]);
            }
        }
        return new int[] {supply, buy};
    }

    private void writeData(String toFileName, int[] data) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write("supply," + data[ZERO_INDEX] + System.lineSeparator()
                    + "buy," + data[FIRST_INDEX] + System.lineSeparator()
                    + "result," + (data[ZERO_INDEX] - data[FIRST_INDEX]));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
