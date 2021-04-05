package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX_CONST = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stringArray = line.split(REGEX_CONST);
                if (stringArray[0].equals("supply")) {
                    supply += Integer.parseInt(stringArray[1]);
                } else if (stringArray[0].equals("buy")) {
                    buy += Integer.parseInt(stringArray[1]);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("supply").append(",").append(supply).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(supply - buy);
        File file = new File(toFileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(builder.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        }
    }
}
