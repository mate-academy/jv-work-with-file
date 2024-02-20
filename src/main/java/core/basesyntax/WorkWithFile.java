package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;

        StringBuilder builder = new StringBuilder();
        readFromFile(fromFileName, builder);
        String[] dataFromFile = builder.toString().split(System.lineSeparator());
        createReport(buy, supply, builder, dataFromFile);
        writeToFile(toFileName, builder);

    }

    private void createReport(int buy, int supply, StringBuilder builder, String[] dataFromFile) {
        builder.setLength(0);
        for (String line : dataFromFile) {
            String[] records = line.split(COMMA);
            if (records[0].equals(BUY)) {
                buy += Integer.valueOf(records[1]);
            }
            if (records[0].equals(SUPPLY)) {
                supply += Integer.valueOf(records[1]);
            }
        }
        int result;
        result = supply - buy;
        builder.append(SUPPLY + COMMA).append(supply).append(System.lineSeparator())
                .append(BUY + COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT + COMMA).append(result);
    }

    private void readFromFile(String fromFileName, StringBuilder builder) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int value = bufferedReader.read();
            while (value != -1) {
                builder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }

    private void writeToFile(String toFileName, StringBuilder builder) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }
}
