package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.*;

public class WorkWithFile {
    public static final String REGEX = " ";
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String RESULT_REGEX = ",";
    public static final String RESULT_NAME = "result";

    public static void getStatistic(String fromFileName, String toFileName) {
        String fileName = toFileName;
        File readFile = new File(fromFileName);

        StringBuilder builder = new StringBuilder();
        String value;
        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            while (reader.ready()) {
                builder.append(reader.readLine()).append(REGEX);
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't read data from file " + readFile, exception);
        }
        String formattedData = new FormattData().getFormattedData(builder.toString());
        new WriteData().writeDataToFile(formattedData, toFileName);
    }
}


