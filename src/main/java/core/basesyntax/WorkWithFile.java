package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DATA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataOfDay = readFromFile(fromFileName);
        creatReport(dataOfDay, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(new File(fromFileName)))) {
            String value = bufferedReader.readLine();
            if (value.length() == 0) {
                return new String[0];
            }
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file" + fromFileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private void creatReport(String[] dataOfDay, String toFileName) {
        int supplySum = 0;
        int boughtSum = 0;
        for (String str : dataOfDay) {
            String[] strs = str.split(DATA_SEPARATOR);
            if (str.contains("supply")) {
                supplySum += Integer.parseInt(strs[1]);
            }
            if (str.contains("buy")) {
                boughtSum += Integer.parseInt(strs[1]);
            }
        }
        int result = supplySum - boughtSum;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(boughtSum).append(System.lineSeparator())
                .append("result,").append(result);
        writeToFile(builder.toString(), toFileName);
    }

    private void writeToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(new File(toFileName), true))) {
            bufferedWriter.write(data.toCharArray());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
