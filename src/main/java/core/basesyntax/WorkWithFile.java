package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ARRAY_INDEX_ZERO = 0;
    private static final int ARRAY_INDEX_ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(createReportData(fromFileName, toFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private int countSupply(String fromFileName, String toFileName) {
        int countSupply = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] splitString = value.split(",");
                if (splitString[ARRAY_INDEX_ZERO].equals("supply")) {
                    countSupply += Integer.parseInt(splitString[ARRAY_INDEX_ONE]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return countSupply;
    }

    private int countBuy(String fromFileName, String toFileName) {
        int countBuy = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] splitString = value.split(",");
                if (splitString[ARRAY_INDEX_ZERO].equals("buy")) {
                    countBuy += Integer.parseInt(splitString[ARRAY_INDEX_ONE]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return countBuy;
    }

    private String createReportData(String fromFileName, String toFileName) {
        int result = countSupply(fromFileName, toFileName) - countBuy(fromFileName, toFileName);
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(countSupply(fromFileName, toFileName))
                .append(System.lineSeparator());
        builder.append("buy,").append(countBuy(fromFileName, toFileName))
                .append(System.lineSeparator());
        builder.append("result,").append(result);
        String report = builder.toString();
        return report;
    }
}
