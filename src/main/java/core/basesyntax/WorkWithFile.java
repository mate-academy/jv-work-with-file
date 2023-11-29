package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int ACTION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileContent = readFromFile(fromFileName);
        String report = prepareReport(fileContent);
        writeToFile(report, toFileName);
    }

    private String [] readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(";");
                value = reader.readLine();
            }
            return stringBuilder.toString().split(";");
        } catch (IOException e) {
            throw new RuntimeException("can`t read file", e);
        }
    }

    private String prepareReport(String[] fileContent) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (int i = 0; i < fileContent.length; i++) {
            String[] data = fileContent[i].split(",");
            switch (data[ACTION_TYPE_INDEX]) {
                case SUPPLY:
                    supplyAmount += Integer.parseInt(data[AMOUNT_INDEX]);
                    break;
                case BUY:
                    buyAmount += Integer.parseInt(data[AMOUNT_INDEX]);
                    break;
                default:
                    throw new RuntimeException("Report type should be "
                            + SUPPLY + " or " + BUY);
            }
        }
        StringBuilder report = new StringBuilder();
        return report.append(SUPPLY).append(",").append(supplyAmount)
                .append(System.lineSeparator()).append(BUY).append(",").append(buyAmount)
                .append(System.lineSeparator()).append("result").append(",")
                .append(supplyAmount - buyAmount).toString();

    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write data to file.", e);
        }
    }
}


