package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    private static final int SUPPLY = 0;
    private static final int BUY = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        List<String> readedData = readData(fromFileName);

        int[] data;

        data = sortData(readedData);

        writeData(toFileName, data);
    }

    public List<String> readData(String fromFileName) {
        File fromFile = new File(fromFileName);
        try {
            return Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    public int[] sortData(List<String> readedData) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < readedData.size(); i++) {
            String[] strings = readedData.get(i).split(",");
            if (strings[0].equals("supply")) {
                supply += Integer.parseInt(strings[1]);
            } else {
                buy += Integer.parseInt(strings[1]);
            }
        }
        return new int[] {supply, buy};
    }

    public void writeData(String toFileName, int[] data) {
        File toFile = new File(toFileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile));
            bufferedWriter.write("supply," + data[SUPPLY] + System.lineSeparator()
                    + "buy," + data[BUY] + System.lineSeparator()
                    + "result," + Integer.sum(data[SUPPLY], -data[BUY]));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
