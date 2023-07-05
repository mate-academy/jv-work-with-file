package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String SEPARATION_SYMBOL = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int countSupply = 0;
        int countBuy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] values = line.split(SEPARATION_SYMBOL);
                if (values[0].equals("supply")) {
                    countSupply += Integer.parseInt(values[1]);
                } else {
                    countBuy += Integer.parseInt(values[1]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("The file was not read", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(getReport(countSupply, countBuy));
        } catch (IOException e) {
            throw new RuntimeException("Data was not written to the file",e);
        }

    }

    private String getReport(int countSupply, int countBuy) {
        int result = countSupply - countBuy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY)
                .append(SEPARATION_SYMBOL)
                .append(countSupply)
                .append(SEPARATOR)
                .append(BUY)
                .append(SEPARATION_SYMBOL)
                .append(countBuy)
                .append(SEPARATOR)
                .append(RESULT)
                .append(SEPARATION_SYMBOL)
                .append(result);
        return report.toString();
    }
}
