package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String emptyLine = "";
    private static final String gap = " ";
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int ALL_SUPPLY_INDEX = 0;
    private static final int ALL_BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readData(fromFileName)), toFileName);
    }

    public String readData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String readData) {
        String withoutSigns = readData
                .replaceAll("[%$#@`~;:+{()_.!/?*',\n\r]", gap)
                .toLowerCase();
        String[] reportData = withoutSigns.split(gap);
        int allSupply = 0;
        int allBuy = 0;
        int result = 0;
        for (int i = 0; i < reportData.length; i += 2) {
            if (reportData[i].equals(emptyLine)) {
                i -= 1;
                continue;
            }
            if (reportData[i].equals(BUY)) {
                allBuy += Integer.parseInt(reportData[i + 1]);
            }
            if (reportData[i].equals(SUPPLY)) {
                allSupply += Integer.parseInt(reportData[i + 1]);
            }
        }
        result = allSupply - allBuy;
        return emptyLine + allSupply + gap + allBuy + gap + result;
    }

    public void writeToFile(String createdReport, String toFileName) {
        String[] report = createdReport.split(gap);
        int allSupply = Integer.parseInt(report[ALL_SUPPLY_INDEX]);
        int allBuy = Integer.parseInt(report[ALL_BUY_INDEX]);
        int result = Integer.parseInt(report[RESULT_INDEX]);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY + SEPARATOR);
            writer.write(Integer.toString(allSupply));
            writer.write(System.lineSeparator());
            writer.write(BUY + SEPARATOR);
            writer.write(Integer.toString(allBuy));
            writer.write(System.lineSeparator());
            writer.write(RESULT + SEPARATOR);
            writer.write(Integer.toString(result));
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't close the file" + toFileName, e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open file" + toFileName, e);
        }
    }
}
