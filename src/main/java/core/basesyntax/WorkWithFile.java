package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int START_POSITION = 0;
    private static final int INFO_ABOUT_POSITION = 1;
    private static final int NEXT_POSITION = 2;
    private static final int SUPPLY_COUNT = 0;
    private static final int BUY_COUNT = 1;
    private static final int RESULT_COUNT = 2;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DATA_DELIMITER_REGEX = "\\W+";

    public void getStatistic(String fromFileName, String toFileName) {

        writeToFile(append(statistic(fileContent(fromFileName))), toFileName);
    }

    private static String fileContent(String fromFileName) {

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value)
                        .append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file " + fromFileName, e);
        }
        return builder.toString();
    }

    private static String[] statistic(String readFile) {

        int supply = START_POSITION;
        int buy = START_POSITION;
        String[] info = readFile.split(DATA_DELIMITER_REGEX);
        for (int i = START_POSITION; i < info.length; i += NEXT_POSITION) {
            if (info[i].equals(SUPPLY)) {
                supply += Integer.parseInt(info[i + INFO_ABOUT_POSITION]);
            }
            if (info[i].equals(BUY)) {
                buy += Integer.parseInt(info[i + INFO_ABOUT_POSITION]);
            }
        }
        int result = supply - buy;
        return new String[]{
                Integer.toString(supply),
                Integer.toString(buy),
                Integer.toString(result)};
    }

    private static String append(String[] calculate) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY)
                .append(",")
                .append(calculate[SUPPLY_COUNT])
                .append(System.lineSeparator())
                .append(BUY)
                .append(",")
                .append(calculate[BUY_COUNT])
                .append(System.lineSeparator())
                .append(RESULT)
                .append(",")
                .append(calculate[RESULT_COUNT])
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private static void writeToFile(String append, String toFileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(append);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

}
