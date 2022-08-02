package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName,
                        true))) {
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value)
                        .append(",");
                value = bufferedReader.readLine();
            }
            String stringBuilder = builder.toString().replace("[^A-Za-z0-9]", " ");
            String [] arrayFromFileName = stringBuilder.split(",");
            int supplyTotal = 0;
            int buyTotal = 0;
            int resultTotal;
            for (int i = 0; i < arrayFromFileName.length; i++) {
                if (arrayFromFileName[i].equals("supply")) {
                    supplyTotal += Integer.parseInt(arrayFromFileName[i + 1]);
                } else if (arrayFromFileName[i].equals("buy")) {
                    buyTotal += Integer.parseInt(arrayFromFileName[i + 1]);
                }
            }
            resultTotal = supplyTotal - buyTotal;
            String[] results = {"supply," + supplyTotal, "buy," + buyTotal,
                    "result," + resultTotal};
            for (String res : results) {
                bufferedWriter.write(res + System.lineSeparator());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't reader file", e);
        } catch (IOException r) {
            throw new RuntimeException("Can't reader file", r);
        }
    }
}
