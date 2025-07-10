package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fileIn = new File(fromFileName);
        File fileOut = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        int buySum = 0;
        int supplySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileIn))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] data = value.split(",");
                if (data[0].equals("supply")) {
                    supplySum += Integer.parseInt(data[1]);
                }
                if (data[0].equals("buy")) {
                    buySum += Integer.parseInt(data[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut))) {
            bufferedWriter.write(stringBuilder
                    .append("supply,").append(supplySum).append(System.lineSeparator())
                    .append("buy,").append(buySum).append(System.lineSeparator())
                    .append("result,").append(supplySum - buySum).toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
