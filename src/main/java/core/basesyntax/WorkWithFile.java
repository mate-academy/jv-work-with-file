package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        writeCsvFile(toFileName, readCsvFile(fromFileName, totalSupply, totalBuy));
    }

    private int[] readCsvFile(String fromFileName, int totalSupply, int totalBuy) {
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] readCsvData = line.split(",");
                switch (readCsvData[0]) {
                    case "supply":
                        totalSupply += Integer.parseInt(readCsvData[1]);
                        break;
                    case "buy":
                        totalBuy += Integer.parseInt(readCsvData[1]);
                        break;
                    default:
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return new int[]{totalSupply, totalBuy};
    }

    private void writeCsvFile(String toFileName, int[] statistics) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            createReport(bw, statistics);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    private void createReport(BufferedWriter bw, int[] statistics) throws IOException {
        bw.write("supply," + statistics[0] + System.lineSeparator());
        bw.write("buy," + statistics[1] + System.lineSeparator());
        bw.write("result," + (statistics[0] - statistics[1]) + System.lineSeparator());
    }
}
