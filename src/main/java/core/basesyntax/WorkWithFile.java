package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String[] FILE_FIELDS
            = new String[] {"supply", "buy", "result"};
    private static final String DELIMITER = ",";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2; //I know it isn't in use, but it might be needed at some point

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> list = readFile(fromFileName);
        writeFile(toFileName, reportStatistics(list));
    }

    private static List<String> readFile(String filePath) {
        File fileToRead = new File(filePath);
        List<String> readData = null;
        try {
            readData = Files.readAllLines(fileToRead.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file "
                    + fileToRead.getName(), e);
        }
        return readData;
    }

    private static void writeFile(String filePath, String data) {
        File fileToWrite = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite))) {
            bw.write(data);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException("Wow... something went wrong,"
                    + "couldn't write the " + fileToWrite.getName(), e);
        }
    }

    private static String reportStatistics(List<String> list) {
        int supply = 0;
        int buy = 0;

        for (String line : list) {
            if (line.contains(FILE_FIELDS[SUPPLY_INDEX])) {
                supply += Integer.parseInt(line.split(DELIMITER)[1]);
            }
            if (line.contains(FILE_FIELDS[BUY_INDEX])) {
                buy += Integer.parseInt(line.split(DELIMITER)[1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        int[] results = new int[]{supply, buy, supply - buy};
        int i = 0;

        for (String fileField : FILE_FIELDS) {
            sb.append(fileField).append(",").append(results[i++]).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
