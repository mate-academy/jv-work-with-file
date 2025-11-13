package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String SEPARATOR = ",";

    private static class ReportData {
        private int supply;
        private int buy;

        public int getSupply() {
            return supply;
        }

        public void setSupply(int amount) {
            this.supply += amount;
        }

        public int getBuy() {
            return buy;
        }

        public void setBuy(int amount) {
            this.buy += amount;
        }
    }

    private ReportData readAndCalculateData(String fromFileName) {
        ReportData data = new ReportData();

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            String category;
            int amount;

            while ((line = br.readLine()) != null) {
                String[] lineArr = line.split(SEPARATOR);
                category = lineArr[0];
                amount = Integer.parseInt(lineArr[1]);

                if (category.equals(SUPPLY)) {
                    data.setSupply(amount);
                } else if (category.equals(BUY)) {
                    data.setBuy(amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    private String createReport(ReportData data) {
        int result = data.getSupply() - data.getBuy();

        return SUPPLY + SEPARATOR + data.getSupply() + System.lineSeparator()
                + BUY + SEPARATOR + data.getBuy() + System.lineSeparator()
                + RESULT + SEPARATOR + result;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        ReportData data = readAndCalculateData(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }
}
