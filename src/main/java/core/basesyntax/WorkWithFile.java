package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = "\r\n";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

        String[] names = stringBuilder.toString().split(DELIMITER);
        int sumSupply = 0;
        int sumBuy = 0;
        for (String name : names) {
            String[] results = name.split(COMMA);
            if (results[0].equals(SUPPLY)) {
                int sum = Integer.parseInt(results[1]);
                sumSupply += sum;
            }
            if (results[0].equals(BUY)) {
                int sum = Integer.parseInt(results[1]);
                sumBuy += sum;
            }
        }

        File file1 = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1))) {
            bufferedWriter.write("supply," + sumSupply + System.lineSeparator()
                    + "buy," + sumBuy + System.lineSeparator()
                    + "result," + (sumSupply - sumBuy));
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}
