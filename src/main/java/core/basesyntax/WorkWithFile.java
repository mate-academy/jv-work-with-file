package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;
    private static final int ZERO = 0;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = getReport(data);
        writerToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fileName, e);
        }
    }

    private String getReport(String data) {
        int buy = ZERO;
        int supply = ZERO;

        String[] counter = data.split(System.lineSeparator());
        for (String line : counter) {
            String[] splitData = line.split("\\W+");
            if (splitData[FIRST_COLUMN].equals(SUPPLY)) {
                supply += Integer.parseInt(splitData[SECOND_COLUMN]);
            } else {
                buy += Integer.parseInt(splitData[SECOND_COLUMN]);
            }
        }
        int result = supply - buy;
        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + "result," + result + System.lineSeparator();
    }

    private void writerToFile(String result, String toFileName) {
        File reporter = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reporter))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
