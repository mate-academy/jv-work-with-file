package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            String result = builder.toString();
            String[] split = result.split(",");
            File file1 = new File(toFileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
            StringBuilder builder1 = new StringBuilder("supply,");
            for (int i = 0; i < split.length; i++) {
                if (split[i].equals("supply")) {
                    supplyCount += Integer.parseInt(split[i + 1]);
                }
                if (split[i].equals("buy")) {
                    buyCount += Integer.parseInt(split[i + 1]);
                }
            }
            builder1.append(supplyCount).append("\nbuy,").append(buyCount)
                    .append("\nresult,").append(supplyCount - buyCount);
            String report = builder1.toString();
            writer.write(report);
            writer.close();
        } catch (IOException e) {
            System.out.println("Can`t read a file");
        }
    }
}
