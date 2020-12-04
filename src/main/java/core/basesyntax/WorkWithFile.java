package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    private static final String[] FILE_FIELDS
            = new String[] {"supply", "buy", "result"};

    private static List<String> readFile(String filePath) {
        File fileToRead = new File(filePath);
        List<String> readData = null;
        try {
            readData = Files.readAllLines(fileToRead.toPath());
        } catch (IOException e) {
            throw new RuntimeException("I wasn't able to read the file", e);
        }
        return readData;
    }

    private static void writeFile(String filePath, List<String> data) {
        File fileToWrite = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite))) {
            for (int i = 0; i < data.size(); i++) {
                bw.write(data.get(i));
                if (i <= data.size() - 1) {
                    bw.write("\n");
                }
                bw.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Wow... something went wrong, couldn't write the file");
        }
    }

    private static List<String> reportStatistics(List<String> list) {
        int supply = 0;
        int buy = 0;

        for (String line : list) {
            if (line.contains(FILE_FIELDS[0])) {
                supply += Integer.parseInt(line.split(",")[1]);
            }
            if (line.contains(FILE_FIELDS[1])) {
                buy += Integer.parseInt(line.split(",")[1]);
            }
        }

        return reportConstructor(supply, buy);
    }

    private static List<String> reportConstructor(int supply, int buy) {
        List<String> list = new ArrayList<>();
        list.add(new StringBuilder().append(FILE_FIELDS[0])
                .append(",").append(supply).append("\n").toString());
        list.add(new StringBuilder().append(FILE_FIELDS[1])
                .append(",").append(buy).append("\n").toString());
        list.add(new StringBuilder().append(FILE_FIELDS[2])
                .append(",").append(supply - buy).append("\n").toString());
        return list;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> list = readFile(fromFileName);
        writeFile(toFileName, reportStatistics(list));
    }
}
