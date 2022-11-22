package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String COMA_REGEX = ",";
    private static final String STRING_BUY = "buy";
    private static final String STRING_SUPPLY = "supply";
    private static final String STRING_RESULT = "result";
    private static final int BUY_SUPPLY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeIntoNewFile(report, toFileName);
    }

    private int createUpdatedSupplySum(int value) {
        int supplySum = 0;
        return supplySum + value;
    }

    private int createUpdatedBuySum(int value) {
        int buySum = 0;
        return buySum + value;
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file!", e);
        }
    }

    public String createReport(String dataFromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        int updatedBuySum = 0;
        int updatedSupplySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new StringReader(dataFromFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] temporaryValue = line.split(COMA_REGEX);
                if (temporaryValue[BUY_SUPPLY_INDEX].equals(STRING_BUY)) {
                    updatedBuySum += createUpdatedBuySum(Integer.parseInt(temporaryValue[VALUE_INDEX]));
                } else {
                    updatedSupplySum += createUpdatedSupplySum(Integer.parseInt(temporaryValue[VALUE_INDEX]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file!", e);
        }
        return stringBuilder
                .append(STRING_SUPPLY).append(COMA_REGEX).append(updatedSupplySum)
                .append(System.lineSeparator())
                .append(STRING_BUY).append(COMA_REGEX).append(updatedBuySum)
                .append(System.lineSeparator())
                .append(STRING_RESULT).append(COMA_REGEX).append(updatedSupplySum - updatedBuySum)
                .toString();
    }

    private void writeIntoNewFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file!", e);
        }
    }
}
