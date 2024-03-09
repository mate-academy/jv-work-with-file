package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int OPERATION_NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String readedData = readFile(fromFileName);
        String report = createReport(readedData);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        try {
            return Files.readString(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createReport(String readedData) {
        int totalBuy = 0;
        int totalSupply = 0;
        String[] words = readedData.split(LINE_SEPARATOR);
        for (String word : words) {
            String[] parts = word.split(",");
            if (parts[OPERATION_NAME_INDEX].equals("supply")) {
                totalSupply += Integer.parseInt(parts[VALUE_INDEX]);
            } else if (parts[OPERATION_NAME_INDEX].equals("buy")) {
                totalBuy += Integer.parseInt(parts[VALUE_INDEX]);
            }
        }

        int result = totalSupply - totalBuy;
        return "supply," + totalSupply + LINE_SEPARATOR
                + "buy," + totalBuy + LINE_SEPARATOR
                + "result," + result;
    }

    private void writeToFile(String report, String fileName) {
        try {
            Files.write(Paths.get(fileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
