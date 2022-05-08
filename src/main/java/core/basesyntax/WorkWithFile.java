package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        Report report = readReportDataFromFile(fromFileName);
        writeReportToFile(toFileName, report.createReport().toString());
    }

    private void writeReportToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    private Report readReportDataFromFile(String fileName) {
        Report report = new Report();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                int amount = Integer.parseInt(value.split(SEPARATOR)[1]);
                if (value.contains(Report.ReportFieldName.SUPPLY.name().toLowerCase())) {
                    report.setSupplySum(report.getSupplySum() + amount);
                } else if (value.contains(Report.ReportFieldName.BUY.name().toLowerCase())) {
                    report.setBuySum(report.getBuySum() + amount);
                }
                value = bufferedReader.readLine();
            }
            return report;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }
}
