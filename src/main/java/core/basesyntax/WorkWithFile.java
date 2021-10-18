package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;

        //String report = reportPrint(supplyCount, buyCount);
        // writeToFile(toFileName, report);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String row = reader.readLine();
            while (row != null) {
                String name = row.substring(0, row.indexOf(","));
                String number = row.substring(row.indexOf(",") + 1);
                if (name.equals("supply")) {
                    supplyCount += Integer.valueOf(number);
                }
                if (name.equals("buy")) {
                    buyCount += Integer.valueOf(number);
                }
                row = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }

        String report = reportPrint(supplyCount, buyCount);
        writeToFile(toFileName, report);
    }

    public String reportPrint(int supply, int buy) {
        StringBuilder report = new StringBuilder();
        return (report.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy)).toString();
    }

    public void writeToFile(String toFileName, String reportPrint) {

        try (BufferedWriter write = new BufferedWriter(new FileWriter(toFileName, true))) {
            write.write(reportPrint);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
