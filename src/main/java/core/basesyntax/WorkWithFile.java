package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_NUMBER_INDEX = 0;
    private static final int BUY_NUMBER_INDEX = 1;
    private static final int RESULT_NUMBER_INDEX = 2;

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
            int amount = Integer.parseInt(splittedRecord[1]);
            if (splittedRecord[0].equals("buy")) {
                countBuy += amount;
            }
            if (splittedRecord[0].equals("supply")) {
                countSupply += amount;
            }
        }
        int result = countSupply - countBuy;
        return new int[]{countSupply, countBuy, result};
    }

    private String createReport(int[] reportNumbers) {
        return new StringBuilder()
                .append("supply,").append(reportNumbers[SUPPLY_NUMBER_INDEX])
                .append(System.lineSeparator())
                .append("buy,").append(reportNumbers[BUY_NUMBER_INDEX])
                .append(System.lineSeparator())
                .append("result,").append(reportNumbers[RESULT_NUMBER_INDEX]).toString();
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
