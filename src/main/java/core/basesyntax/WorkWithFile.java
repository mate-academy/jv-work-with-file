package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String NAME_OF_SUPPLY = "supply";
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final String REGEX_SPLIT = "[,\\s]";
    public static final String CONTENT_REGEX_SPLIT = "\\s+";
    public static final String REPORT_PATTERN = "supply,%d%n"
            + "buy,%d%n"
            + "result,%d%n";

    public void getStatistic(String fromFileName, String toFileName) {
        String content = read(fromFileName);
        content = createReport(content);
        write(content, toFileName);
    }

    public String read(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file" + fromFileName, e);
        }
        return builder.toString();
    }

    private String createReport(String content) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String[] contentLines = content.split(CONTENT_REGEX_SPLIT);
        for (String line : contentLines) {
            String[] values = line.split(REGEX_SPLIT);
            switch (values[OPERATION_INDEX]) {
                case NAME_OF_SUPPLY:
                    supplyAmount += Integer.parseInt(values[AMOUNT_INDEX]);
                    break;
                default:
                    buyAmount += Integer.parseInt(values[AMOUNT_INDEX]);
                    break;
            }
        }
        int result = supplyAmount - buyAmount;
        return String.format(REPORT_PATTERN, supplyAmount, buyAmount, result);
    }

    private void write(String content, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(content);
        } catch (IOException d) {
            throw new RuntimeException("Cannot write data to file" + toFileName, d);
        }
    }
}
