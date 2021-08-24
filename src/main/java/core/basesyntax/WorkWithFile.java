package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(fromFileName, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String dataFromFile = bufferedReader.readLine();
            while (dataFromFile != null) {
                stringBuilder.append(dataFromFile).append(" ");
                dataFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File doesn't exist", e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(getResult(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

    private int calculateSupply(String data) {
        String[] list = data.split(" ");
        int supply = 0;
        for (String value : list) {
            if (value.substring(0, value.indexOf(',')).equals("supply")) {
                supply += Integer.parseInt(value.substring(value.indexOf(',') + 1));
            }
        }
        return supply;
    }

    private int calculateBuy(String data) {
        String[] list = data.split(" ");
        int buy = 0;
        for (String value : list) {
            if (value.substring(0, value.indexOf(',')).equals("buy")) {
                buy += Integer.parseInt(value.substring(value.indexOf(',') + 1));
            }
        }
        return buy;
    }

    private String getResult(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int result = calculateSupply(readFromFile(fromFileName))
                - calculateBuy(readFromFile(fromFileName));
        return stringBuilder.append("supply,")
                .append(calculateSupply(readFromFile(fromFileName)))
                .append(System.lineSeparator()).append("buy,")
                .append(calculateBuy(readFromFile(fromFileName)))
                .append(System.lineSeparator()).append("result,")
                .append(result).toString();
    }
}
