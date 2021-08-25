package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] columns;
        int supply = 0;
        int buy = 0;
        int result = 0;
        try {
            String[] fromFile = Files.newBufferedReader(Paths.get(fromFileName))
                        .lines()
                        .collect(Collectors.joining(" "))
                        .split(" ");
            for (String s : fromFile) {
                columns = s.split(SEPARATOR);
                if (columns[FIRST_COLUMN].equals(SUPPLY)) {
                    supply += Integer.parseInt(columns[SECOND_COLUMN]);
                    result += Integer.parseInt(columns[SECOND_COLUMN]);
                }
                if (columns[FIRST_COLUMN].equals(BUY)) {
                    buy += Integer.parseInt(columns[SECOND_COLUMN]);
                    result -= Integer.parseInt(columns[SECOND_COLUMN]);
                }
            }
            stringBuilder.append(SUPPLY)
                        .append(SEPARATOR)
                        .append(supply)
                        .append(System.lineSeparator())
                        .append(BUY)
                        .append(SEPARATOR)
                        .append(buy)
                        .append(System.lineSeparator())
                        .append(RESULT)
                        .append(SEPARATOR)
                        .append(result);
            Files.write(Paths.get(toFileName), stringBuilder.toString().getBytes());

        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e);
        }
    }
}
