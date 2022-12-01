package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t find the file", e);
        }
        String[] split = stringBuilder.toString().split(",");
        int buyAmount = 0;
        int supplyAmount = 0;
        for (int i = 0; i < split.length; i += 2) {
            if (split[i].equals("supply")) {
                supplyAmount += Integer.parseInt(split[i + 1]);
            } else {
                buyAmount += Integer.parseInt(split[i + 1]);
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2
                .append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(supplyAmount - buyAmount);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(stringBuilder2.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can`t close BufferedWriter", e);
                }
            }
        }
    }
}
