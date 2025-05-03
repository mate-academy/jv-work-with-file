package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int PROCESS_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String NEW_LINE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        int[] resultArray = readFile(fromFileName);
        String report = generateReport(resultArray);
        writeFile(toFileName, report);
    }

    private int[] readFile(String fromFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(COMMA);
                int value = Integer.parseInt(parts[VALUE_INDEX]);
                if (parts[PROCESS_INDEX].equals(SUPPLY)) {
                    supply += value;
                } else {
                    buy += value;
                }
                line = reader.readLine();
            }
            return new int[]{supply, buy};
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private String generateReport(int [] resultArray) {
        int supply = resultArray[PROCESS_INDEX];
        int buy = resultArray[VALUE_INDEX];
        int result = supply - buy;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY)
                .append(COMMA)
                .append(supply)
                .append(NEW_LINE)
                .append(BUY)
                .append(COMMA)
                .append(buy)
                .append(NEW_LINE)
                .append(RESULT)
                .append(COMMA)
                .append(result);
        return reportBuilder.toString();
    }

    private void writeFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);

        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
