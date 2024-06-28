package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {

            int supplyOutput = 0;
            int buyOutput = 0;
            String value;

            while ((value = bufferedReader.readLine()) != null) {
                String[] spitedLine = value.split(",");
                String name = spitedLine[0];
                int amount = Integer.parseInt(spitedLine[1]);

                if ("supply".equals(name)) {
                    supplyOutput += amount;
                } else if ("buy".equals(name)) {
                    buyOutput += amount;
                }
            }

            int result = supplyOutput - buyOutput;
            bufferedWriter.write("supply," + supplyOutput);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buyOutput);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);

        } catch (IOException e) {
            throw new RuntimeException("Error processing files", e);
        }
    }
}
