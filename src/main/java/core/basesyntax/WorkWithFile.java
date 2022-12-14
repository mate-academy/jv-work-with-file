package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String NAME_SUPPLY = "supply";
    private static final String NAME_BUY = "buy";
    private static final String NAME_RESULT = "result";
    private static final int DATA_NUMBER = 1;
    public int supplyData = 0;
    public int buyData = 0;


    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write changes", e);
        }
        getSupplyData(fromFileName);
        for (String row : getReportData()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
                writer.write(row + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }

    public void getSupplyData(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String rowFromFile = reader.readLine();
            String[] separateData;
            while (rowFromFile != null) {
                if (rowFromFile.contains(NAME_SUPPLY)) {
                    separateData = rowFromFile.split(",");
                    supplyData += Integer.parseInt(separateData[DATA_NUMBER]);
                } else if (rowFromFile.contains(NAME_BUY)) {
                    separateData = rowFromFile.split(",");
                    buyData += Integer.parseInt(separateData[DATA_NUMBER]);
                }
                rowFromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file, e);
        }
    }

    public String[] getReportData() {
        String result = String.valueOf((supplyData - buyData));
        String[] report = new String[3];
        report[0] = NAME_SUPPLY + "," + String.valueOf(supplyData);
        report[1] = NAME_BUY + "," + String.valueOf(buyData);
        report[2] = NAME_RESULT + "," + result;
        supplyData = 0;
        buyData = 0;
        return report;
    }
}
