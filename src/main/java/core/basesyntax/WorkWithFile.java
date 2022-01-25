package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromMarket = readData(fromFileName);
        int[] supplyAndBuyInfo = countData(dataFromMarket);
        String report = createReport(supplyAndBuyInfo);
        writeToFile(toFileName, report);
    }

    private String[] readData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("I can't read read file" + fromFileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private int[] countData(String[] dataFromMarket) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < dataFromMarket.length; i++) {
            if (dataFromMarket[i].contains("supply")) {
                supply += Integer.parseInt(dataFromMarket[i].substring(dataFromMarket[i]
                        .indexOf(",") + 1));
            } else if (dataFromMarket[i].contains("buy")) {
                buy += Integer.parseInt(dataFromMarket[i].substring(dataFromMarket[i]
                        .indexOf(",") + 1));
            }
        }
        return new int[] {supply, buy};
    }

    private String createReport(int[] supplyAndBuyInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("supply,").append(supplyAndBuyInfo[SUPPLY_INDEX])
                .append(System.lineSeparator())
                .append("buy,").append(supplyAndBuyInfo[BUY_INDEX])
                .append(System.lineSeparator())
                .append("result,").append(supplyAndBuyInfo[SUPPLY_INDEX]
                        - supplyAndBuyInfo[BUY_INDEX]).toString();
    }

    private void writeToFile(String toFileName, String report) {
        try {
            File toFilFile = new File(toFileName);
            toFilFile.createNewFile();
        } catch (Exception e) {
            throw new RuntimeException("I can't create file" + toFileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (Exception e) {
            throw new RuntimeException("I can't write information to the file" + toFileName, e);
        }
    }
}
