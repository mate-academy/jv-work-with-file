package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(readFromFile(fromFileName).toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file" + toFileName, e);
        }
    }

    private StringBuilder readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            StringBuilder result = new StringBuilder();
            int supplyCounter = 0;
            int buyCounter = 0;
            while (line != null) {
                String[] information = line.split(",");
                if ("supply".equals(information[0])) {
                    supplyCounter += Integer.parseInt(information[1]);
                } else {
                    buyCounter += Integer.parseInt(information[1]);
                }
                line = bufferedReader.readLine();
            }
            result.append("supply,").append(supplyCounter).append(System.lineSeparator());
            result.append("buy,").append(buyCounter).append(System.lineSeparator());
            result.append("result,").append(supplyCounter - buyCounter);
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Can`t find or read file" + fromFileName, e);
        }
    }
}
