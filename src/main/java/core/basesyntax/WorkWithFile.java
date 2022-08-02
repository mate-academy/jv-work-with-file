package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_TYPE_COLUMN = 0;
    private static final int AMOUNT_COLUMN = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        File fromFile = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                if (splitLine[OPERATION_TYPE_COLUMN].equals(SUPPLY)) {
                    supplySum += Integer.parseInt(splitLine[AMOUNT_COLUMN]);
                } else if (splitLine[OPERATION_TYPE_COLUMN].equals(BUY)) {
                    buySum += Integer.parseInt(splitLine[AMOUNT_COLUMN]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open the file", e);
        }
        String result = getResult(supplySum, buySum);
        writeToFile(toFileName, result);
    }

    private String getResult(int supplySum, int buySum) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(",").append(supplySum).append(System.lineSeparator())
                .append(BUY).append(",").append(buySum).append(System.lineSeparator())
                .append(RESULT).append(",").append(supplySum - buySum);
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String content) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
