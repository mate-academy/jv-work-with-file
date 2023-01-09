package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readFromFile(fromFileName)));
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error: file not found: ", e);
        }
    }

    private String createReport(String dataFromFile) {
        int buyQuantity = 0;
        int supplyQuantity = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] split = dataFromFile.split(" ");
        for (String arrays:split) {
            if ("buy".equals(arrays.replaceAll("\\W\\d+", ""))) {
                buyQuantity += Integer.parseInt(arrays.replaceAll("\\D", ""));
            }
            if ("supply".equals(arrays.replaceAll("\\W\\d+", ""))) {
                supplyQuantity += Integer.parseInt(arrays.replaceAll("\\D", ""));
            }
        }
        return stringBuilder.append("supply").append(",").append(supplyQuantity)
                .append(System.lineSeparator()).append("buy").append(",")
                .append(buyQuantity).append(System.lineSeparator())
                .append("result").append(",").append(supplyQuantity - buyQuantity).toString();
    }

    private void writeToFile(String toFileName, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file: ", e);
        }
    }
}
