package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final int POSITION_NAME = 0;
    private static final int POSITION_SUM = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = read(fromFileName);
        write(toFileName, calculateReport(data));
    }

    public String read(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private String calculateReport(String data) {
        String[] arrayLines = data.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String str : arrayLines) {
            String[] position = str.split(COMMA);
            if (position[POSITION_NAME].equals(SUPPLY)) {
                supply += Integer.parseInt(position[POSITION_SUM]);
            }
            if (position[POSITION_NAME].equals(BUY)) {
                buy += Integer.parseInt(position[POSITION_SUM]);
            }
        }
        return SUPPLY + COMMA + supply + System.lineSeparator()
                + BUY + COMMA + buy + System.lineSeparator()
                + RESULT + COMMA + (supply - buy);
    }

    private void write(String toFileName, String calculateReport) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(calculateReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file", e);
        }
    }
}



