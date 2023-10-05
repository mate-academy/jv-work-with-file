package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyResult = 0;
        int buyResult = 0;
        File fromFile = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                if (splitLine[0].equals("supply")) {
                    supplyResult += Integer.parseInt(splitLine[1]);
                } else {
                    buyResult += Integer.parseInt(splitLine[1]);
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + fromFileName, e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supplyResult).append(System.lineSeparator());
        stringBuilder.append("buy,").append(buyResult).append(System.lineSeparator());
        stringBuilder.append("result,").append(supplyResult - buyResult);
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + toFileName, e);
        }
    }
}
