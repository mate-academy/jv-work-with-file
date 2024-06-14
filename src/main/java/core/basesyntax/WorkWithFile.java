package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyOutput = 0;
        int buyOutput = 0;
        int result = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] spitedLine = value.split(",");
                String name = spitedLine[0];
                value = bufferedReader.readLine();
                int amount = Integer.parseInt(spitedLine[1]);

                if (name.equals("supply")) {
                    supplyOutput += amount;
                } else if (name.equals("buy")) {
                    buyOutput += amount;
                }
                result = supplyOutput - buyOutput;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write("supply," + supplyOutput);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buyOutput);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
