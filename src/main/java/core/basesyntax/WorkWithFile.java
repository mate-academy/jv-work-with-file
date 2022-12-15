package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String NAME_SUPPLY = "supply";
    private static final String NAME_BUY = "buy";
    private static final String NAME_RESULT = "result";
    private static final int DATA_NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        getDataWritten(fromFileName, toFileName);
    }

    public String[] getReportData(String fromFileName) {
        int supplyData = 0;
        int buyData = 0;
        File file = new File(fromFileName);
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            String rowFromFile = reader.readLine();
            String[] separateData;
            while (rowFromFile != null) {
                separateData = rowFromFile.split(",");
                if (rowFromFile.contains(NAME_SUPPLY)) {
                    supplyData += Integer.parseInt(separateData[DATA_NUMBER]);
                } else if (rowFromFile.contains(NAME_BUY)) {
                    buyData += Integer.parseInt(separateData[DATA_NUMBER]);
                }
                rowFromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file, e);
        }
        String result = String.valueOf((supplyData - buyData));
        String[] report = new String[3];
        report[0] = NAME_SUPPLY + "," + String.valueOf(supplyData);
        report[1] = NAME_BUY + "," + String.valueOf(buyData);
        report[2] = NAME_RESULT + "," + result;
        return report;
    }

    private void getDataWritten(String fromFileName, String toFileName) {
        for (String row : getReportData(fromFileName)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
                writer.write(row + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
