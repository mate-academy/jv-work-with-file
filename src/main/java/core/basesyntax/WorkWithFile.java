package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int AMOUNT_INDEX = 1;
    private static final int TYPE_INDEX = 0;
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String text = readFileAndCreateReport(fromFileName);
        writeReportToFile(toFileName, text);
    }

    private String readFileAndCreateReport(String fromFile) {
        int supplyAmount = 0;
        int buyAmount = 0;
        int resultAmount;
        StringBuilder reportBuilder = new StringBuilder();
        try (BufferedReader reader
                     = new BufferedReader(new FileReader(fromFile))) {
            String data = reader.readLine();
            while (data != null) {
                String[] line = data.split(",");
                if (line[TYPE_INDEX].equals(SUPPLY)) {
                    supplyAmount += Integer.parseInt(line[AMOUNT_INDEX]);
                } else {
                    buyAmount += Integer.parseInt(line[AMOUNT_INDEX]);
                }
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        resultAmount = supplyAmount - buyAmount;
        return reportBuilder.append(SUPPLY).append(",")
                .append(supplyAmount).append(System.lineSeparator())
                .append(BUY).append(",").append(buyAmount).append(System.lineSeparator())
                .append(RESULT).append(",").append(resultAmount).toString();
    }
    
    private void writeReportToFile(String toFile, String text) {
        try (BufferedWriter writer
                     = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
