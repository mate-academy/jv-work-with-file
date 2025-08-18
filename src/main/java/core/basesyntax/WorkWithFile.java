package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        int result = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] parts = value.split(",");
                if (parts.length == 2) {
                    if (parts[0].equals(SUPPLY)) {
                        supplySum += Integer.parseInt(parts[1]);
                    }
                    if (parts[0].equals(BUY)) {
                        buySum += Integer.parseInt(parts[1]);
                    }
                }
                value = bufferedReader.readLine();
            }
            result = supplySum - buySum;
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Can`t process with file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supplySum + "\n");
            bufferedWriter.write("buy," + buySum + "\n");
            bufferedWriter.write("result," + result + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Can`t process with file", e);
        }
    }
}
