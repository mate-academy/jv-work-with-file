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

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        if (file.exists()) {
            return;
        }
        for (String row : getReport(fromFileName)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
                writer.write(row + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }

    public int getSupplyData(String fromFileName) {
        File file = new File(fromFileName);
        int supplyData = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String rowFromFile = reader.readLine();
            String[] separateData = new String[2];
            while (rowFromFile != null) {
                if (rowFromFile.contains(NAME_SUPPLY)) {
                    separateData = rowFromFile.split(",");
                    supplyData += Integer.parseInt(separateData[1]);
                }
                rowFromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file, e);
        }
        return supplyData;
    }

    public int getBuyData(String fromFileName) {
        File file = new File(fromFileName);
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String rowFromFile = reader.readLine();
            String[] separateData = new String[2];
            while (rowFromFile != null) {
                if (rowFromFile.contains(NAME_BUY)) {
                    separateData = rowFromFile.split(",");
                    buy += Integer.parseInt(separateData[1]);
                }
                rowFromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file, e);
        }
        return buy;
    }

    public String[] getReport(String fromFileName) {
        String[] report = new String[3];
        report[0] = NAME_SUPPLY + "," + String.valueOf(getSupplyData(fromFileName));
        report[1] = NAME_BUY + "," + String.valueOf(getBuyData(fromFileName));
        report[2] = NAME_RESULT + "," + String.valueOf(getSupplyData(fromFileName)
                - getBuyData(fromFileName));
        return report;
    }
}
