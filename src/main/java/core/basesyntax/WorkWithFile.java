package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writing(calculating(reading(fromFileName)),toFileName);
    }

    private String reading(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String reader = bufferedReader.readLine();
            while (reader != null) {
                stringBuilder.append(reader).append(" ");
                reader = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
        return stringBuilder.toString();
    }

    private String[] calculating(String reader) {
        StringBuilder stringBuilder = new StringBuilder(reader);
        String[] array = stringBuilder.toString().split(" ");
        int supplyCount = 0;
        int buyCount = 0;
        for (String element : array) {
            String[] split = element.split(",");
            if (split[0].equals("supply")) {
                supplyCount += Integer.parseInt(split[1]);
            }
            if (split[0].equals("buy")) {
                buyCount += Integer.parseInt(split[1]);
            }
        }
        stringBuilder = new StringBuilder();
        String[] report = {stringBuilder.append("supply")
                .append(",")
                .append(supplyCount).toString(),
                stringBuilder.append(System.lineSeparator())
                        .append("buy")
                        .append(",")
                        .append(buyCount).toString(),
                stringBuilder.append(System.lineSeparator())
                        .append("result")
                        .append(",")
                        .append(supplyCount - buyCount).toString()};
        return report;
    }

    private void writing(String[] report, String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
        for (String line : report) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write(line);
                bufferedWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException("Can`t write data to file", e);
            }
        }
    }
}
