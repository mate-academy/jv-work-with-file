package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String statistic = getData(fromFileName);
        reportData(statistic, toFileName);
    }

    private String getData(String fromFileName) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fromFileName))) {
            int supplyAmount = 0;
            int buyAmount = 0;
            while (bufferedReader.ready()) {
                String[] line = bufferedReader.readLine().split(",");
                if (line[0].equals("supply")) {
                    supplyAmount += Integer.parseInt(line[1]);
                } else {
                    buyAmount += Integer.parseInt(line[1]);
                }
            }
            return getReport(supplyAmount, buyAmount);
        } catch (IOException e) {
            throw new RuntimeException("Can't find or read the file", e);
        }
    }

    private String getReport(int supplyAmount, int buyAmount) {
        int result = supplyAmount - buyAmount;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supplyAmount).append(System.lineSeparator());
        stringBuilder.append("buy,").append(buyAmount).append(System.lineSeparator());
        stringBuilder.append("result,").append(result);
        return stringBuilder.toString();
    }

    private void reportData(String statistic, String toFileName) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(toFileName))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in the file", e);
        }
    }
}
