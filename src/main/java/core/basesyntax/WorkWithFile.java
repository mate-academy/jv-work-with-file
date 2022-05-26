package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_AMOUNT = 1;
    private static final int ZERO_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String inputData = readFromFile(fromFileName);
        String statistic = getStatistic(inputData);
        writeToFile(toFileName, statistic);
    }

    private String getStatistic(String inputDate) {
        int countSupply = 0;
        int countBuy = 0;
        String[] dataFromFile = inputDate.split(" ");
        for (String data : dataFromFile) {
            String[] splitComa = data.split(",");
            if (splitComa[ZERO_INDEX].equals("buy")) {
                countBuy += Integer.parseInt(splitComa[INDEX_OF_AMOUNT]);
            } else {
                countSupply += Integer.parseInt(splitComa[INDEX_OF_AMOUNT]);
            }
        }
        int result = countSupply - countBuy;
        StringBuilder statisticBuilder = new StringBuilder();
        return statisticBuilder.append("supply").append(",").append(countSupply)
                .append(System.lineSeparator()).append("buy")
                .append(",").append(countBuy).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append(" ");
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can not read this file:" + fromFileName, e);
        }
    }


    private void writeToFile(String toFileName, String statistic) {
        File file = new File(toFileName);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(statistic);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can not write this file: " + toFileName, e);
        }
    }
}
