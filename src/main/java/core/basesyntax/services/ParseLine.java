package core.basesyntax.services;

public class ReportService {
    private int sumBuy = 0;
    private int sumSupply = 0;

    public void process(String dataLine) {
        if (lineText == null) {
            return;
        }
        String[] dataLine = lineText.split(",");
        if ("buy".equals(dataLine[0])) {
            sumBuy += Integer.parseInt(dataLine[1]);
        }
        if ("supply".equals(dataLine[0])) {
            sumSupply += Integer.parseInt(dataLine[1]);
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
