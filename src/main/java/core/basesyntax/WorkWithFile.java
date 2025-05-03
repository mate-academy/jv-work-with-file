package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        if (fromFileName.isEmpty()) {
            return;
        }
        String[] entries = getTableEntries(fromFileName);
        String[] reportData = getReportData(entries);

        StringBuilder reportBuilder = new StringBuilder();

        for (String entry : reportData) {
            reportBuilder.append(entry).append(System.lineSeparator());
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportBuilder.toString().trim() + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to this file: " + toFileName, e);
        }
    }

    public String[] getTableEntries(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder data = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                data.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return data.toString().split(System.lineSeparator());

        } catch (FileNotFoundException e) {
            throw new RuntimeException("There is no file with such name: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file: " + fromFileName, e);
        }
    }

    public String[] getReportData(String[] tableData) {
        StringBuilder report = new StringBuilder();
        int buyAmount = 0;
        int supplyAmount = 0;

        try {
            for (String entry : tableData) {
                String[] data = entry.split(",");
                if (data[0].equals("buy")) {
                    buyAmount += Integer.parseInt(data[1]);
                } else if (data[0].equals("supply")) {
                    supplyAmount += Integer.parseInt(data[1]);
                }
            }
        } catch (RuntimeException e) {
            throw new ArrayIndexOutOfBoundsException("Wrong data format");
        }
        report.append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(supplyAmount - buyAmount);
        return report.toString().split(System.lineSeparator());
    }
}
