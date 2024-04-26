package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_OPTION = "buy";
    private static final String SUPPLY_OPTION = "supply";
    private static final String RESULT_OPTION = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            int sumBuy = 0;
            int sumSupply = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String type = parts[0].trim().toLowerCase();
                    int amount = Integer.parseInt(parts[1]);
                    if (BUY_OPTION.equals(type)) {
                        sumBuy += amount;
                    } else if (SUPPLY_OPTION.equals(type)) {
                        sumSupply += amount;
                    }
                }
            }
            int result = sumSupply - sumBuy;
            writeToFile(bufferedWriter, SUPPLY_OPTION, sumSupply);
            writeToFile(bufferedWriter, BUY_OPTION, sumBuy);
            writeToFile(bufferedWriter, RESULT_OPTION, result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read or write a file", e);
        }
    }

    private void writeToFile(BufferedWriter writer, String option, int value) throws IOException {
        writer.write(option + "," + value);
        writer.newLine();
    }
}
