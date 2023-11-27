package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int ACTION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String [] fileContent = readFromFile(fromFileName);
        String report = prepareReport(fileContent);
        writeToFile(report, toFileName);
    }

    private String [] readFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(";");
            }

            return stringBuilder.toString().split(";");
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private String prepareReport(String [] fileContent) {
        StringBuilder report = new StringBuilder();
        int supplyAmount = 0;
        int buyAmount = 0;

        for (String fileContentLine : fileContent) {
            String[] typeAmountData = fileContentLine.split(",");
            switch (typeAmountData[ACTION_TYPE_INDEX]) {
                case SUPPLY:
                    supplyAmount = supplyAmount
                        + Integer.parseInt(typeAmountData[AMOUNT_INDEX]);
                    break;
                case BUY:
                    buyAmount = buyAmount
                        + Integer.parseInt(typeAmountData[AMOUNT_INDEX]);
                    break;
                default:
                    throw new IllegalArgumentException("Report type should be "
                        + SUPPLY + " or " + BUY);
            }
        }

        return report.append(SUPPLY).append(",").append(supplyAmount)
            .append(System.lineSeparator()).append(BUY).append(",").append(buyAmount)
            .append(System.lineSeparator()).append("result").append(",")
            .append(supplyAmount - buyAmount).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (FileWriter writer = new FileWriter(toFileName, false)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write data to file.", e);
        }
    }
}
