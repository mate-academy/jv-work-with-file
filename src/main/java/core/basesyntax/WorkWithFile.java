package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final int FIRST_ELEMENT = 1;
    private static final int ZERO_ELEMENT = 0;
    private StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stringArray = line.split(CSV_SEPARATOR);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        }
    }

    private void getResult(String[] stringArray) {
        int buy = 0;
        int supply = 0;
        if (stringArray[ZERO_ELEMENT].equals(SUPPLY_WORD)) {
            supply += Integer.parseInt(stringArray[FIRST_ELEMENT]);
        } else if (stringArray[ZERO_ELEMENT].equals(BUY_WORD)) {
            buy += Integer.parseInt(stringArray[FIRST_ELEMENT]);
        }
        builder.append(SUPPLY_WORD).append(",").append(SUPPLY_WORD).append(System.lineSeparator())
                .append(BUY_WORD).append(",").append(BUY_WORD).append(System.lineSeparator())
                .append("result").append(",").append(supply - buy);
    }

    private void setStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        }
    }
}
