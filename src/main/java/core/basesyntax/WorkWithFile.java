package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            String[] temporaryArray;
            int totalSupply = 0;
            int totalBuy = 0;
            while ((line = bufferedReader.readLine()) != null) {
                temporaryArray = line.split(",");
                if (temporaryArray[0].equals("supply")) {
                    totalSupply += Integer.parseInt(temporaryArray[1]);
                } else if (temporaryArray[0].equals("buy")) {
                    totalBuy += Integer.parseInt(temporaryArray[1]);
                }
            }
            createReportFile(toFileName, totalSupply, totalBuy);
        } catch (IOException e) {
            throw new RuntimeException("Can't read info from file", e);
        }
    }

    private void createReportFile(String reportName, int supplyInt, int buyInt) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportName))) {
            bufferedWriter.write("supply," + supplyInt);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buyInt);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + (supplyInt - buyInt));
        } catch (IOException e) {
            throw new RuntimeException("Can't write info in file", e);
        }
    }
}
