package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final int INDEX_OF_AMOUNT = 1;
    private static final int ZERO_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String inputData = readFromFile(fromFileName);
        String statistic = getStatistic(inputData);
        writeToFile(toFileName, statistic);
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
            throw new RuntimeException("Can not read this fila:" + fromFileName, e);
        }
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
        StringBuilder count = new StringBuilder();
        return count.append("supply").append(",").append(countSupply).append("\n").append("buy")
                .append(",").append(countBuy).append("\n")
                .append("result,").append(result).toString();
    }

    private void writeToFile(String toFileName, String calculatorData) {
        File file = new File(toFileName);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(calculatorData);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can not write this fail: " + toFileName, e);
        }
    }
}
