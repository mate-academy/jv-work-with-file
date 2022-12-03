package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int INDEX_ACTION = 0;
    private static final int INDEX_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    public List<String> readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        List<String> listFromFile;
        try {
            listFromFile = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from " + fromFileName, e);
        }
        return listFromFile;
    }

    public String createReport(List<String> dataFromFile) {
        StringBuilder result = new StringBuilder();
        int buy = 0;
        int supply = 0;
        String[] dividedString;

        for (String s : dataFromFile) {
            dividedString = s.split(",");
            if (dividedString[INDEX_ACTION].equals("buy")) {
                buy += Integer.parseInt(dividedString[INDEX_AMOUNT]);
            } else {
                supply += Integer.parseInt(dividedString[INDEX_AMOUNT]);
            }
        }

        result.append("supply,").append(supply).append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator()).append("result,")
                .append(supply - buy);

        return result.toString();
    }

    public void writeToFile(String report, String toFileName) {
        File toFile = new File(toFileName);

        try {
            Files.write(toFile.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + toFileName, e);
        }
    }
}
