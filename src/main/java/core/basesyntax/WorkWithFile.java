package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file1 = new File(fromFileName);
        File file2 = new File(toFileName);
        StringBuilder builder = new StringBuilder();
        int totalSupply = 0;
        int totalBuy = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        String[] records = builder.toString().split(System.lineSeparator());
        StringBuilder builderResult = new StringBuilder();

        for (String record : records) {
            if (record.contains("supply")) {
                totalSupply += Integer.parseInt(record.substring(record.indexOf(",") + 1));
            }
            if (record.contains("buy")) {
                totalBuy += Integer.parseInt(record.substring(record.indexOf(",") + 1));
            }
        }

        builderResult.append("supply,").append(totalSupply)
                .append(System.lineSeparator()).append("buy,")
                .append(totalBuy).append(System.lineSeparator()).append("result,")
                .append(totalSupply - totalBuy).append(System.lineSeparator());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2))) {
            bufferedWriter.write(builderResult.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
