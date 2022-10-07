package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CHARACTER_S = "s";
    private static final String CHARACTER_B = "b";
    private static final String SPLIT_REGEX_COMMA = ",";
    private static final int WORD_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName);
        writeToFile(toFileName, report);
    }

    private String createReport(String fileName) {
        int supply = 0;
        int buy = 0;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] line = value.split(SPLIT_REGEX_COMMA);
                if (line[WORD_INDEX].startsWith(CHARACTER_S)) {
                    supply += Integer.parseInt(line[AMOUNT_INDEX]);
                } else if (line[WORD_INDEX].startsWith(CHARACTER_B)) {
                    buy += Integer.parseInt(line[AMOUNT_INDEX]);
                }
                value = reader.readLine();
            }
            int result = supply - buy;
            builder.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
