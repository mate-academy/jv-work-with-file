package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String RESULT_SUPPLY = "supply";
    private static final String RESULT_BUY = "buy";
    private static final String RESULT_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int countSupply = 0;
        int countBuy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operation = parts[0].trim();
                    int amount = Integer.parseInt(parts[1].trim());
                    if (operation.equals(OPERATION_SUPPLY)) {
                        countSupply += amount;
                    }
                    if (operation.equals(OPERATION_BUY)) {
                        countBuy += amount;
                    }
                }
            }
            int result = countSupply - countBuy;

            bufferedWriter.write(RESULT_SUPPLY + "," + countSupply);
            bufferedWriter.newLine();
            bufferedWriter.write(RESULT_BUY + "," + countBuy);
            bufferedWriter.newLine();
            bufferedWriter.write(RESULT_RESULT + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file:", e);
        }
    }
}
