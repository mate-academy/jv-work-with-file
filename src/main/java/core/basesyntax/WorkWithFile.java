package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        File writeFile = new File(toFileName);
        int supplyAmount = 0;
        int buyAmount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFile));) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(";");
                value = reader.readLine();
            }
            String[] table = stringBuilder.toString().split(";");

            for (int i = 0; i < table.length; i++) {
                String[] data = table[i].split(",");
                switch (data[0]) {
                    case "supply":
                        supplyAmount += Integer.parseInt(data[1]);
                        break;
                    case "buy":
                        buyAmount += Integer.parseInt(data[1]);
                        break;
                    default: break;
                }
            }
            StringBuilder stringBuildera = new StringBuilder();
            stringBuildera.append("supply").append(",").append(supplyAmount)
                    .append(System.lineSeparator()).append("buy").append(",").append(buyAmount)
                    .append(System.lineSeparator()).append("result").append(",")
                    .append(supplyAmount - buyAmount);
            bufferedWriter.write(stringBuildera.toString());

        } catch (IOException e) {
            throw new RuntimeException("can`t do it", e);
        }
    }
}
