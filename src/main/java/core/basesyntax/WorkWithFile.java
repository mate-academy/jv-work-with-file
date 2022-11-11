package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";
    private static final int ZERO_INDEX = 0;
    private static final int ONE_INDEX = 1;
    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = calculateData(readFile(fromFileName));
        writeResult(toFileName, report);

    }

    public String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found ", e);
        }
        return builder.toString();
    }

    public String calculateData(String data) {
        String [] array = data.split(" ");
        for (String arr : array) {
            String[] split = arr.split(",");
            if (split[ZERO_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(split[ONE_INDEX]);
            }
            if (split[ZERO_INDEX].equals(BUY)) {
                buy += Integer.parseInt(split[ONE_INDEX]);
            }
        }
        int result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(result);
        return builder.toString();
    }

    public void writeResult(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(report);
            bufferedWriter.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Somethigs was wrong.", e);
        }
    }
}

