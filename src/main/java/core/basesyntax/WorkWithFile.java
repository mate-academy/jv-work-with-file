package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SEPARATOR = "\\W+";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader buffered = new BufferedReader((new FileReader(fromFileName)))) {
            String value = buffered.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = buffered.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] data = builder.toString().split(SEPARATOR);
        int addSupply = 0;
        int addBuy = 0;
        int result;
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(SUPPLY)) {
                addSupply += Integer.parseInt(data[i + 1]);
            }
            if (data[i].equals(BUY)) {
                addBuy += Integer.parseInt(data[i + 1]);

            }
        }
        result = addSupply - addBuy;
        StringBuilder builder1 = new StringBuilder().append(SUPPLY)
                .append(COMA).append(addSupply).append(System.lineSeparator())
                .append(BUY).append(COMA).append(addBuy).append(System.lineSeparator())
                .append("result").append(COMA).append(result);

        try (BufferedWriter buffered = new BufferedWriter(new FileWriter(toFileName, true))) {
            buffered.write(builder1.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
