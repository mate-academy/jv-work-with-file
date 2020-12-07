package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String OPERATION_TYPE_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder counting = new StringBuilder();
        int buy = 0;
        int supply = 0;
        try (BufferedReader statistic = new BufferedReader(new FileReader(fromFileName))) {
            String data = statistic.readLine();
            while (data != null) {
                String[] check = data.split(",");
                if (check[0].equals(OPERATION_TYPE_BUY)) {
                    buy += Integer.parseInt(check[1]);
                } else {
                    supply += Integer.parseInt(check[1]);
                }
                data = statistic.readLine();
            }
            counting.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply - buy).append(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fromFileName, e);
        }
        writeReport(toFileName, counting);
    }

    private void writeReport(String toFileName, StringBuilder counting) {
        File file = new File(toFileName);
        try (BufferedWriter inputStat = new BufferedWriter(new FileWriter(file))) {
            inputStat.write(counting.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
