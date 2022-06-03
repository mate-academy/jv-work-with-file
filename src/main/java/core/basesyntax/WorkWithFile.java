package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final char SEPARATOR = ',';
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder str = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            int numberOfSupply = 0;
            int numberOfBuy = 0;
            int index;
            while (value != null) {
                index = value.indexOf(',');
                if (value.substring(0,index).equals(SUPPLY)) {
                    numberOfSupply += parseInt(value.substring(index + 1));
                } else {
                    numberOfBuy += parseInt(value.substring(index + 1));
                }
                value = reader.readLine();
            }

            str.append(SUPPLY + SEPARATOR).append(numberOfSupply).append(System.lineSeparator());
            str.append(BUY + SEPARATOR).append(numberOfBuy).append(System.lineSeparator());
            str.append(RESULT + SEPARATOR).append(numberOfSupply - numberOfBuy)
                    .append(System.lineSeparator())
                    .append("\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read data from file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data", e);
        }

        File fileResult = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileResult, true))) {
            bufferedWriter.write(str.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file", e);
        }
    }
}
