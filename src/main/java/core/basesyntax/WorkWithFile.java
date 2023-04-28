package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply,";
    public static final String BUY = "buy,";
    public static final String RESULT = "result,";
    public static final String DELIMETER = ",";
    private StringBuilder stringBuilder = new StringBuilder();
    private int buy = 0;
    private int supply = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readData(fromFileName);
        extracted(supply,buy);
        writeData(buy, supply, toFileName, stringBuilder.toString());
    }

    private void extracted(int supply, int buy) {
        stringBuilder.append(SUPPLY)
                    .append(supply)
                    .append(System.lineSeparator())
                    .append(BUY)
                    .append(buy)
                    .append(System.lineSeparator())
                    .append(RESULT)
                    .append(supply - buy)
                    .append(System.lineSeparator());
    }

    public static boolean isFileExists(File file) {
        return file.exists();
    }

    private void analysisData(String value) {
        String[] arrayData = value.split(DELIMETER);
        String equalsWord = "buy";
        if (arrayData[0].equals(equalsWord)) {
            buy += Integer.parseInt(arrayData[1]);
        } else {
            supply += Integer.parseInt(arrayData[1]);
        }
    }

    private void readData(String fromFileName) {
        File file = new File(fromFileName);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                //stringBuilder.append(value).append(System.lineSeparator());
                analysisData(value);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeData(int b, int s, String toFileName, String d) {
        File file = new File(toFileName);
        if (!isFileExists(file)) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(d);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data", e);
            }
        }
    }
}
