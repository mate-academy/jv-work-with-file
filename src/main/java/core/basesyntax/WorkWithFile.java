package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            int totalSupply = readData(fromFileName, SUPPLY);
            int totalBuy = readData(fromFileName, BUY);
            int result = calculateResult(totalSupply, totalBuy);
            writeDataToFile(toFileName, totalSupply, totalBuy, result);
        } catch (IOException e) {
            throw new RuntimeException("Error processing files: " + fromFileName
                    + " and " + toFileName, e);
        }
    }

    private int readData(String fileName, String operation) throws IOException {
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (operation.equals(parts[0])) {
                    try {
                        int amount = Integer.parseInt(parts[1]);
                        total += amount;
                    } catch (NumberFormatException e) {
                        throw new IOException("Invalid amount format in file: " + fileName, e);
                    }
                }
            }
        }
        return total;
    }

    private int calculateResult(int totalSupply, int totalBuy) {
        return totalSupply - totalBuy;
    }

    private void writeDataToFile(String fileName, int totalSupply, int totalBuy,
                                 int result) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            StringBuilder sb = new StringBuilder();
            sb.append(SUPPLY)
                    .append(",")
                    .append(totalSupply)
                    .append(System.lineSeparator());
            sb.append(BUY)
                    .append(",")
                    .append(totalBuy)
                    .append(System.lineSeparator());
            sb.append("result")
                    .append(",")
                    .append(result)
                    .append(System.lineSeparator());
            writer.write(sb.toString());
        }
    }
}
