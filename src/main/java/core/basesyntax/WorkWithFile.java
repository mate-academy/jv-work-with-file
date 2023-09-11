package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String SPASE = " ";
    private static final String SIGN = "\\W+";
    public void getStatistic(String fromFileName, String toFileName) {
        String[] splitArray = readLine(fromFileName);
        String calculateSolution = calculateStatistic(splitArray);
        writeToFail(toFileName, calculateSolution);
    }

    private String[] readLine(String filePath) {
        File file = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        String[] splitArray;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SPASE);
                value = reader.readLine();
            }

            splitArray = stringBuilder.toString().split(SIGN);
            return splitArray;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    private String calculateStatistic(String[] splitArray) {
        int sumSupply = 0;
        int sumBuy = 0;

        for (int i = 0; i < splitArray.length; i++) {
            if (splitArray[i].equals("supply")) {
                sumSupply += Integer.parseInt(splitArray[i + 1]);
                i++;
            } else {
                sumBuy += Integer.parseInt(splitArray[i + 1]);
                i++;
            }
        }

        int calculation = sumSupply - sumBuy;

        String result = "supply," + sumSupply + SEPARATOR
            + "buy," + sumBuy + SEPARATOR
            + "result," + calculation;

        return result;
    }

    private void writeToFail(String toFilePath, String result) {
        File file = new File(toFilePath);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
