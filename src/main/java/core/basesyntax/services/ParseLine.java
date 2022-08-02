package core.basesyntax.services;

public class ReportService {
    private int sumBuy = 0;
    private int sumSupply = 0;

    public void run(String lineText) {
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

    public String printSumBuy() {
        return "buy," + sumBuy + System.lineSeparator();
    }

    public String printSumSupply() {
        return "supply," + sumSupply + System.lineSeparator();
    }

    public String printResult() {
        return "result," + (sumSupply - sumBuy);
    }
}
