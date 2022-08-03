package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File was not found!");
        }
        String[] splitWords = stringBuilder.toString().split("\\W+");

        int sumOfSupply = 0;
        int sumOfBuy = 0;
        for (int i = 1; i < splitWords.length;i++) {
            if (splitWords[i - 1].equals(SUPPLY)) {
                sumOfSupply += Integer.parseInt(splitWords[i]);
            } else if (splitWords[i - 1].equals(BUY)) {
                sumOfBuy += Integer.parseInt(splitWords[i]);
            }
        }
        int result = sumOfSupply - sumOfBuy;

        try {
            File file = new File(toFileName);
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file!", e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + COMMA + sumOfSupply + System.lineSeparator());
            bufferedWriter.write(BUY + COMMA + sumOfBuy + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("File was not found!");
        }
    }
}
