package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                String operation = split[0];
                int number = Integer.parseInt(split[1]);
                if (operation.equals(SUPPLY)) {
                    supply += number;
                }
                if (operation.equals(BUY)) {
                    buy += number;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from a file",e);
        }
        result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY)
                     .append(",")
                     .append(supply)
                     .append(System.lineSeparator())
                     .append(BUY)
                     .append(",")
                     .append(buy)
                     .append(System.lineSeparator())
                     .append(RESULT)
                     .append(",")
                     .append(result);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to a file",e);
        }
    }
}
