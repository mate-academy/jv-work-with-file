package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private int buyResult = 0;
    private int supplyResult = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] array = value.split(",");
                if (array[0].equals("buy")) {
                    int temp = Integer.parseInt(array[1]);
                    buyResult += temp;
                } else if (array[0].equals("supply")) {
                    int temp = Integer.parseInt(array[1]);
                    supplyResult += temp;
                }
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,")
                    .append(supplyResult)
                    .append(System.lineSeparator())
                    .append("buy,")
                    .append(buyResult)
                .append(System.lineSeparator())
                .append("result,")
                .append(supplyResult - buyResult).toString();
        String s = stringBuilder.toString();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));) {
            bufferedWriter.write(s);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}





