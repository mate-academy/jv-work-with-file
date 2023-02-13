package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String regex = " ";
        File readFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        String value;
        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            while ((value = reader.readLine()) != null) {
                builder.append(value).append(regex);
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't read data from file " + readFile, exception);
        }
        String formatedData = getFormattedData(builder.toString());
        File managedData = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(managedData))) {
            for (int i = 0; i < formatedData.length(); i++) {
                writer.write(formatedData.charAt(i));
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't write data to file " + managedData, exception);
        }
    }

    public String getFormattedData(String data) {
        int totalSupply = 0;
        int totalBuy = 0;
        int result;
        String regex = " ";
        final String buy = "buy";
        final String supply = "supply";
        final String resultName = "result";
        StringBuilder builder = new StringBuilder();
        String[] arr = data.split(regex);
        for (String tips : arr) {
            int intValue = parseInt(tips.substring(tips.indexOf(",") + 1));
            if (tips.contains("buy")) {
                totalBuy += intValue;
            } else if (tips.contains("supply")) {
                totalSupply += intValue;
            }
        }
        result = totalSupply - totalBuy;
        builder.append(supply).append(",").append(totalSupply).append(System.lineSeparator());
        builder.append(buy).append(",").append(totalBuy).append(System.lineSeparator());
        builder.append(resultName).append(",").append(result);
        return builder.toString();
    }
}

