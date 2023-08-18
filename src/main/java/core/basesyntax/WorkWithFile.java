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
    private static final String CHARACTER = ",";
    private static final String RESULT = "result";

    private String readAndSortFile(String fileName) {
        File file = new File(fileName);
        StringBuilder builder = new StringBuilder();
        int buy = 0;
        int supply = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                String[] splitArray = value.split(CHARACTER);
                if (splitArray[0].equals(BUY)) {
                    buy = buy + Integer.parseInt(splitArray[1]);
                } else if (splitArray[0].equals(SUPPLY)) {
                    supply = supply + Integer.parseInt(splitArray[1]);
                }
                value = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        builder.append(SUPPLY).append(CHARACTER).append(supply).append(System.lineSeparator());
        builder.append(BUY).append(CHARACTER).append(buy).append(System.lineSeparator());
        builder.append(RESULT).append(CHARACTER).append(supply - buy);
        String result = builder.toString();
        return result;
    }

    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(readAndSortFile(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
