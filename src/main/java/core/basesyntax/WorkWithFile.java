package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final int SUPPLY_INDEX = 0;
    private static final String BUY = "buy";
    private static final int BUY_INDEX = 1;
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int buyMoney = 0;
        int supplyMoney = 0;
        StringBuilder reportResult = new StringBuilder();
        String[] data = readReport(fromFileName);
        for (String line : data) {
            String[] splitedReport = line.split(COMMA);
            if (splitedReport[SUPPLY_INDEX].equals(SUPPLY)) {
                supplyMoney += Integer.parseInt(splitedReport[BUY_INDEX]);
            } else {
                buyMoney += Integer.parseInt(splitedReport[BUY_INDEX]);
            }
        }
        reportResult.append(SUPPLY).append(COMMA)
                .append(supplyMoney).append(System.lineSeparator())
                .append(BUY).append(COMMA)
                .append(buyMoney).append(System.lineSeparator())
                .append(RESULT).append(COMMA)
                .append(supplyMoney - buyMoney).append(System.lineSeparator());
        writeReport(toFileName, reportResult);
    }

    private String[] readReport(String fileName) {
        StringBuilder savedInfo = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                savedInfo.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found!", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return savedInfo.toString().split(System.lineSeparator());
    }

    private void writeReport(String fileName, StringBuilder report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't create file!", e);
        }
    }
}
