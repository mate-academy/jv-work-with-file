package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int INDEX_OF_COUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String inputData = readFromFile(fromFileName);
        String calculatorData = calculatorData(inputData);
        writeToFile(toFileName, calculatorData);
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

    private String calculatorData(String inputDate) {
        int countSupply = 0;
        int countBuy = 0;
        String[] dataFromFile = inputDate.split(" ");
        for (String data : dataFromFile) {
            String[] splitComa = data.split(",");
            if (splitComa[0].equals(BUY)) {
                countBuy += Integer.parseInt(splitComa[INDEX_OF_COUNT]);
            } else {
                countSupply += Integer.parseInt(splitComa[INDEX_OF_COUNT]);
            }
        }
        int result = countSupply - countBuy;
        StringBuilder count = new StringBuilder();
        return count.append(SUPPLY).append(",").append(countSupply).append("\n").append(BUY)
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
