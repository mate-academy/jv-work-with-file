package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String RESULT = "result";
    public static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        String report = createReport(getCreateDataForReport(readFromFile(fromFileName)));
        writeFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null) {
                stringBuilder.append(temp).append(System.lineSeparator());
            }
            return stringBuilder.toString().split("\\n");
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file: " + fromFileName, e);
        }
    }

    private int[] getCreateDataForReport(String[] records) {
        int countBuy = 0;
        int countSupply = 0;
        for (String record : records) {
            String[] splittedRecord = record.split(",");
            int amountBuyOrSupply = Integer.parseInt(splittedRecord[1]);
            if (splittedRecord[0].equals(BUY)) {
                countBuy += amountBuyOrSupply;
            }
            if (splittedRecord[0].equals(SUPPLY)) {
                countSupply += amountBuyOrSupply;
            }
        }
        int result = countSupply - countBuy;
        return new int[]{countSupply, countBuy, result};
    }

    private String createReport(int[] reportNumbers) {
        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(reportNumbers[0])
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(reportNumbers[1])
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(reportNumbers[2]).toString();
    }

    private void writeFile(String toFileName, String createReport) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(createReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file");
        }
    }
}
