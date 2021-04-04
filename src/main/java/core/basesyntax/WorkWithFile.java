package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        String result = "";
        StringBuilder stringBuilder = new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fromFileName, e);
        }
        result = stringBuilder.toString();
        return result;
    }

    private String createReport(String inputData) {
        String[] records = inputData.split(" ");
        StringBuilder builder = new StringBuilder();
        int countBuy = 0;
        int countSupply = 0;
        for (String record : records) {
            String[] array = record.split(",");
            if (record.contains("buy")) {
                countBuy += Integer.parseInt(array[1]);
            } else {
                countSupply += Integer.parseInt(array[1]);
            }
        }
        int result = countSupply - countBuy;
        builder.append("supply,").append(countSupply).append(System.lineSeparator())
                .append("buy,").append(countBuy).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator());
        return builder.toString();
    }

    private void writeToFile(String inputData, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(inputData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}

