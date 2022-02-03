package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int INDEX_OF_OPERATION = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readFromFile(fromFileName)));
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            Files.write(file.toPath(), report.getBytes());
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

    private String createReport(List<String> list) {
        StringBuilder builder = new StringBuilder();
        int totalSupply = 0;
        int totalBuy = 0;

        for (String info : list) {
            int indexOfComma = info.indexOf(COMMA);
            if (info.substring(INDEX_OF_OPERATION, indexOfComma).equals(BUY)) {
                totalBuy += Integer.parseInt(info.substring(indexOfComma + 1));
            } else {
                totalSupply += Integer.parseInt(info.substring(indexOfComma + 1));
            }
        }
        return builder.append(SUPPLY).append(COMMA).append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(totalSupply - totalBuy).toString();
    }
}
