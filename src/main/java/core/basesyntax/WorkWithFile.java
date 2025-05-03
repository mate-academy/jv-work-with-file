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
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {

            int sumBuy = 0;
            int sumSupply = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.contains(BUY)) {
                    sumBuy += Integer.parseInt(line.replaceAll("[^0-9]+", ""));
                } else {
                    sumSupply += Integer.parseInt(line.replaceAll("[^0-9]+", ""));
                }
            }

            StringBuilder resul = new StringBuilder();
            resul.append(SUPPLY).append(COMMA).append(sumSupply).append(System.lineSeparator())
                    .append(BUY).append(COMMA).append(sumBuy).append(System.lineSeparator())
                    .append(RESULT).append(COMMA).append(sumSupply - sumBuy);

            bufferedWriter.write(resul.toString());
            bufferedWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }
}
