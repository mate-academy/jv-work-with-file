package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final String FIRST_KEY_WORD = "supply";
    private static final String SECOND_KEY_WORD = "buy";
    private static final String COMMA_SEPARATOR = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public void getStatistic(String fromFileName, String toFileName) {
        File toReading = new File(fromFileName);
        File toWriting = new File(toFileName);
        StringBuilder builder = new StringBuilder();
        int supply = 0;
        int buy = 0;
        String toWrite;

        try {
            String[] fileData = Files.readString(toReading.toPath()).split(NEW_LINE_SEPARATOR);

            for (String line : fileData) {
                String[] lines = line.split(COMMA_SEPARATOR);
                if (lines[FIRST_INDEX].equals(FIRST_KEY_WORD)) {
                    supply += Integer.parseInt(lines[SECOND_INDEX]);
                }
                if (lines[FIRST_INDEX].equals(SECOND_KEY_WORD)) {
                    buy += Integer.parseInt(lines[SECOND_INDEX]);
                }
            }

            int result = supply - buy;
            builder.append("supply,").append(supply).append("\n")
                    .append("buy,").append(buy).append("\n")
                    .append("result,").append(result);
            toWrite = builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + fromFileName, e);
        }

        try {
            Files.writeString(toWriting.toPath(), toWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file" + toFileName, e);
        }
    }
}
