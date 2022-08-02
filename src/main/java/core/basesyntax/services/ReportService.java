package core.basesyntax.services;

public class ReportService {
    private int sumBuy = 0;
    private int sumSupply = 0;

    public void process(String dataLine) {
        if (dataLine == null) {
            return;
        }
        String[] arrayData = dataLine.split(",");
        if ("buy".equals(arrayData[0])) {
            sumBuy += Integer.parseInt(arrayData[1]);
        }
        if ("supply".equals(arrayData[0])) {
            sumSupply += Integer.parseInt(arrayData[1]);
        }
    }

    public String getSumBuy() {
        return "buy," + sumBuy + System.lineSeparator();
    }

    public String getSumSupply() {
        return "supply," + sumSupply + System.lineSeparator();
    }

    public String getResult() {
        return "result," + (sumSupply - sumBuy);
    }
}
