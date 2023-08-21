package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(" ");
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found", e);
        } catch (IOException e) {
            throw new RuntimeException("cant read file", e);
        }

        if (stringBuilder.length() != 0) {
            int supplySum = 0;
            int buySum = 0;

            String[] stringInput = stringBuilder.toString().split("\\W+");
            for (int i = 0; i < count; i++) {
                if (stringInput[i * 2].equals("supply")) {
                    supplySum += Integer.parseInt(stringInput[i * 2 + 1]);
                } else if (stringInput[i * 2].equals("buy")) {
                    buySum += Integer.parseInt(stringInput[i * 2 + 1]);
                }
            }
            StringBuilder result = new StringBuilder();
            result.append("supply,")
                    .append(supplySum)
                    .append("\nbuy,")
                    .append(buySum)
                    .append("\nresult,")
                    .append(supplySum - buySum);
            try (FileWriter fileWriter = new FileWriter(toFileName)) {
                fileWriter.write(String.valueOf(result));
            } catch (IOException e) {
                throw new RuntimeException("cant wrie to file", e);
            }
        }
    }
}
