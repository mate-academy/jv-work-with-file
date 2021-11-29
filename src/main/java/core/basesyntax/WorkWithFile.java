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
        File file1 = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1, true))) {

            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }

            String[] data = stringBuilder.toString().split("[;\n]");

            int sumBuy = 0;
            int sumSupply = 0;
            int index;
            for (String datum : data) {
                if (datum.startsWith("buy")) {
                    index = datum.indexOf(",");
                    sumBuy = sumBuy
                            + Integer.parseInt(datum.substring(index + 1, datum.length() - 1));
                }
                if (datum.startsWith("supply")) {
                    index = datum.indexOf(",");
                    sumSupply = sumSupply
                            + Integer.parseInt(datum.substring(index + 1, datum.length() - 1));
                }
            }
            String report = "supply," + sumSupply + System.lineSeparator()
                    + "buy," + sumBuy + System.lineSeparator()
                    + "result," + (sumSupply - sumBuy);

            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file ", e);
        }
    }
}
