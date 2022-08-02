package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public static void getStatistic(String fromFileName, String toFileName) {
        int buySum = 0;
        int supplySum = 0;
        int result = 0;
        File fileFrom = new File(fromFileName);
        File fileTo = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileFrom));
            String string = reader.readLine();
            while (string != null) {
                stringBuilder.append(string).append(",");
                string = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] strings = stringBuilder.toString().split(",");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(SUPPLY)) {
                supplySum += Integer.parseInt(strings[i + 1]);
            }
            if (strings[i].equals(BUY)) {
                buySum += Integer.parseInt(strings[i + 1]);
            }
            result = supplySum - buySum;
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
            bufferedWriter.write(SUPPLY + "," + supplySum
                    + System.lineSeparator() + BUY + "," + buySum
                    + System.lineSeparator() + RESULT + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
