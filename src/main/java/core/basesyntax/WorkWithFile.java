package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(getReportFromFile(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to File", e);
        }
    }

    private static String getDataFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder fileInfoLineBuilder = new StringBuilder();
            String lineFromFile = reader.readLine();
            while (lineFromFile != null) {
                fileInfoLineBuilder.append(lineFromFile).append(System.lineSeparator());
                lineFromFile = reader.readLine();
            }
            return fileInfoLineBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't find File", e);
        }
    }

    private static String getReportFromFile(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] fileData = getDataFromFile(fromFileName).split(System.lineSeparator());
        for (String data : fileData) {
            int quantityOfGoods = Integer.parseInt(data.replaceAll("[^0-9]", ""));
            if (data.contains("buy")) {
                totalBuy = totalBuy + quantityOfGoods;
            } else {
                totalSupply = totalSupply + quantityOfGoods;
            }
        }
        int result = totalSupply - totalBuy;
        StringBuilder resultDataLineBuilder = new StringBuilder();
        String resultData = resultDataLineBuilder
                .append("supply,").append(totalSupply).append(System.lineSeparator())
                .append("buy,").append(totalBuy).append(System.lineSeparator())
                .append("result,").append(result)
                .toString();
        return resultData;
    }
}
