package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPECIFIED_CHARACTER = "\\W+";
    private static final String Supply = "supply";
    private static final String Buy = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = new String[0];
        int supplyValue = 0;
        int buyValue = 0;
        StringBuilder reportBuilder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
            dataFromFile = builder.toString().split(SPECIFIED_CHARACTER);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }

        for (int j = 0; j < dataFromFile.length; j++) {
            if (dataFromFile[j].equals(Supply)) {
                supplyValue += Integer.parseInt(dataFromFile[j + 1]);
            }
            if (dataFromFile[j].equals(Buy)) {
                buyValue += Integer.parseInt(dataFromFile[j + 1]);
            }
        }

        reportBuilder.append("supply,").append(supplyValue).append(System.lineSeparator())
                .append("buy,").append(buyValue).append(System.lineSeparator())
                .append("result,").append(supplyValue - buyValue);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(reportBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
