package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            int[] totals = calculateTotals(fromFileName);
            writeOutput(toFileName, totals[0], totals[1], totals[0] - totals[1]);
        } catch (IOException e) {
            System.err.println("An error occurred while processing the file: " + e.getMessage());
        }
    }

    private int[] calculateTotals(String fromFileName) throws IOException {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String operationType = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (OPERATION_SUPPLY.equals(operationType)) {
                    totalSupply += amount;
                } else if (OPERATION_BUY.equals(operationType)) {
                    totalBuy += amount;
                }
            }
        }

        return new int[]{totalSupply, totalBuy};
    }

    private void writeOutput(String toFileName, int totalSupply, int totalBuy, int result)
            throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(OPERATION_SUPPLY + "," + totalSupply);
            bw.newLine();
            bw.write(OPERATION_BUY + "," + totalBuy);
            bw.newLine();
            bw.write("result," + result);
        }
    }

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("input.csv", "output.csv");
    }
}
