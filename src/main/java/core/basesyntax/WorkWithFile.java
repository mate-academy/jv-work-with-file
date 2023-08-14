package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String file = readFile(fromFileName);
        String formattedFile = summariseStats(file);
        writeToFile(formattedFile, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder build = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                build.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read this file" + fromFileName, e);
        }
        return build.toString();
    }

    private String summariseStats(String file) {
        StringBuilder builder = new StringBuilder();
        String[] formatted = file.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String line : formatted) {
            String[] splitArray = line.split(COMMA);
            int amount = Integer.parseInt(splitArray[AMOUNT_INDEX]);
            String operation = splitArray[OPERATION_INDEX];
            if (operation.equals(BUY_OPERATION)) {
                buy += amount;
            } else {
                supply += amount;
            }
        }
        int result = supply - buy;
        builder.append(SUPPLY_OPERATION).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY_OPERATION).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return builder.toString();
    }

    private void writeToFile(String formattedFile, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(formattedFile);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to this file" + toFileName, e);
        }
    }
}
