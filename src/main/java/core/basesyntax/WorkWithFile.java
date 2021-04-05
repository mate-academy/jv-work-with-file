package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String PURPOSE_AND_VALUE_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String data = bufferedReader.readLine();
            while (data != null) {
                int valueOfData = Integer.parseInt(data.split(PURPOSE_AND_VALUE_SEPARATOR)[1]);
                if (data.trim().split(PURPOSE_AND_VALUE_SEPARATOR)[0].equals(SUPPLY)) {
                    supplyAmount += valueOfData;
                } else if (data.trim().split(PURPOSE_AND_VALUE_SEPARATOR)[0].equals(BUY)) {
                    buyAmount += valueOfData;
                }
                data = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + fromFileName, e);
        }
        writeToFile(toFileName, createReport(supplyAmount, buyAmount));
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.format("%10s", report));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to" + toFileName, e);
        }
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator())
                .append("result,").append(result)
                .append(System.lineSeparator());
        return reportBuilder.toString();
    }
}

