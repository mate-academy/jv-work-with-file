package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String[] FILE_FIELD = {"supply", "buy", "result"};
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        String report = makeReport(dataFromFile);
        writeFile(toFileName, report);
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file "
                    + file.getName(), e);
        }
    }

    private String makeReport(List<String> dataFromFile) {
        int supplyCunt = 0;
        int buyCount = 0;

        for (String line : dataFromFile) {
            String[] dataArray = line.split(DELIMITER);
            if (dataArray[0].equals(FILE_FIELD[0])) {
                supplyCunt += Integer.parseInt(dataArray[1]);
            }
            if (dataArray[0].equals(FILE_FIELD[1])) {
                buyCount += Integer.parseInt(dataArray[1]);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FILE_FIELD[0]).append(DELIMITER).append(supplyCunt)
                .append(System.lineSeparator())
                .append(FILE_FIELD[1]).append(DELIMITER).append(buyCount)
                .append(System.lineSeparator())
                .append(FILE_FIELD[2]).append(DELIMITER).append(supplyCunt - buyCount);
        return stringBuilder.toString();
    }

    private void writeFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to " + file.getName(), e);
        }
    }
}
