package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        File fileToRead = new File(fromFileName);
        File fileToWrite = new File(toFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite))) {
            int countSupply = 0;
            int countBuy = 0;
            int result = 0;
            String line = reader.readLine();
            while (line != null) {
                String[] values = line.split(",");
                if (values[0].equals("supply")) {
                    countSupply += Integer.parseInt(values[1]);
                } else {
                    countBuy += Integer.parseInt(values[1]);
                }
                line = reader.readLine();
            }
            result = countSupply - countBuy;
            StringBuilder report = new StringBuilder();
            report.append("supply,")
                    .append(countSupply)
                    .append(SEPARATOR)
                    .append("buy,")
                    .append(countBuy)
                    .append(SEPARATOR)
                    .append("result,")
                    .append(result);
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("File not read", e);
        }
    }
}
