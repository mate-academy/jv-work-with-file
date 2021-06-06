package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA = ",";
    private int totalSupply = 0;
    private int totalBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        List<String> list = createReport(readFromFile(fromFileName));

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file", e);
        }

        try {
            for (String row : list) {
                Files.write(file.toPath(), row.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read form file " + fromFileName, e);
        }
    }

    private List<String> createReport(List<String> list) {
        List<String> report = new ArrayList<>();

        for (String info : list) {
            int indexOfComma = info.indexOf(COMMA);
            if (info.substring(0, indexOfComma).contains("buy")) {
                totalBuy += Integer.parseInt(info.substring(indexOfComma + 1));
            } else totalSupply += Integer.parseInt(info.substring(indexOfComma + 1));
        }

        report.add("supply" + COMMA + totalSupply + System.lineSeparator());
        report.add("buy" + COMMA + totalBuy + System.lineSeparator());
        report.add("result" + COMMA + (totalSupply - totalBuy));
        return report;
    }
}
