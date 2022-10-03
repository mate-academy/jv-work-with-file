package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

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
                builder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        return builder.toString().split(" ");
    }

    private void creatReport(String[] dataOfDay, String toFileName) {
        int supplySum = 0;
        int boughtSum = 0;
        for (String str : dataOfDay) {
            if (str.contains("supply")) {
                supplySum += Integer.parseInt(str.split(",")[1]);
            }
            if (str.contains("buy")) {
                boughtSum += Integer.parseInt(str.split(",")[1]);
            }
        }
        int result = supplySum - boughtSum;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(boughtSum).append(System.lineSeparator())
                .append("result,").append(result);
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(new File(toFileName), true))) {
            bufferedWriter.write(builder.toString().toCharArray());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
