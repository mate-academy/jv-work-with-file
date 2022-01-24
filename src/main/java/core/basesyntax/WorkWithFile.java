package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromMarket = readData(fromFileName);
        String report = processData(dataFromMarket);
        createReport(toFileName, report);
    }

    private String[] readData(String fromFileName) {
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

    private String processData(String[] dataFromMarket) {
        stringBuilder.setLength(0);
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
        return stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).toString();
    }

    private void createReport(String toFileName, String report) {
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
