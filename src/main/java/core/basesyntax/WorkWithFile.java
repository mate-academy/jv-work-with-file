package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int ACTION_PART = 0;
    public static final int NUMBERS_PART = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[ACTION_PART].equals("supply")) {
                    supplySum += Integer.parseInt(parts[NUMBERS_PART]);
                } else {
                    buySum += Integer.parseInt(parts[NUMBERS_PART]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supplySum + System.lineSeparator());
            bufferedWriter.write(("buy," + buySum + System.lineSeparator()));
            bufferedWriter.write(("result," + (supplySum - buySum)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
