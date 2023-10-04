package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DIVIDER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            int totalSupply = countTotalSupply(fromFileName);
            int totalBuy = countTotalBuy(fromFileName);
            int result = countResult(totalSupply, totalBuy);

            createReport(writer, totalSupply, totalBuy, result);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong "
                    + fromFileName + " or " + toFileName, e);
        }
    }

    private void createReport(BufferedWriter writer,
                              int totalSupply, int totalBuy, int result) throws IOException {
        writer.write("supply," + totalSupply + System.lineSeparator()
                + "buy," + totalBuy + System.lineSeparator()
                + "result," + result);
        writer.close();
    }

    private int countResult(int totalSupply, int totalBuy) {
        return totalSupply - totalBuy;
    }

    private int countTotalSupply(String fromFileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
        int totalSupply = 0;
        String infoOnLine = reader.readLine();

        while (infoOnLine != null) {
            totalSupply += findSupply(infoOnLine);
            infoOnLine = reader.readLine();
        }

        reader.close();
        return totalSupply;
    }

    private int findSupply(String infoOnLine) {
        String[] info = infoOnLine.split(DIVIDER);
        String status = info[0];
        int price = Integer.parseInt(info[1]);

        if (status.equals("supply")) {
            return price;
        }

        return 0;
    }

    private int countTotalBuy(String fromFileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
        int totalBuy = 0;
        String infoOnLine = reader.readLine();

        while (infoOnLine != null) {
            totalBuy += findBuy(infoOnLine);
            infoOnLine = reader.readLine();
        }

        reader.close();
        return totalBuy;
    }

    private int findBuy(String infoOnLine) {
        String[] info = infoOnLine.split(DIVIDER);
        String status = info[0];
        int price = Integer.parseInt(info[1]);

        if (status.equals("buy")) {
            return price;
        }

        return 0;
    }
}
