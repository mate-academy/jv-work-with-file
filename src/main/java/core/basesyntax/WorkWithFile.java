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
    private static final String STRING_SPLIT = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, readFile(fromFileName));
    }

    private void writeFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        int supply = 0;
        int buy = 0;
        int result = 0;
        File myFile = new File(fromFileName);
        String[] operatingTypeSystem;
        try (BufferedReader reader = new BufferedReader(new FileReader(myFile))) {
            String value = reader.readLine();
            while (value != null) {
                operatingTypeSystem = value.split(STRING_SPLIT);
                if (operatingTypeSystem[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(operatingTypeSystem[1]);
                } else {
                    buy += (Integer.parseInt(operatingTypeSystem[1]));
                }
                value = reader.readLine();
            }
            result = supply - buy;
            builder.append(SUPPLY).append(",").append(supply).append(System.lineSeparator())
                    .append(BUY).append(",").append(buy).append(System.lineSeparator())
                    .append(RESULT).append(",").append(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return String.valueOf(builder);
    }
}
