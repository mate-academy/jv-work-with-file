package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
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

        public void setSupply(int supply) {
            this.supply = supply;
        }

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
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
        return null;
    }

    private void writeToFile(String report, String toFileName) {

    }

    public void getStatistic(String fromFileName, String toFileName) {

        int totalSupply = 0;
        int totalBuy = 0;
    }
}
