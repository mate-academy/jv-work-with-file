package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_ROW = "supply";
    private static final int INDEX_OF_SUPPLY = 0;
    private static final String BUY_ROW = "buy";
    private static final int INDEX_OF_BUY = 1;
    private static final String RESULT_ROW = "result";
    private static final String LINE_SPLITTER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFromFile(fromFileName);

        fileContent = replaceSymbols(fileContent);
        String[] appleArray = fileContent.split(LINE_SPLITTER);
        String[] categoriesArray = {SUPPLY_ROW, BUY_ROW};
        int supplyAmount = 0;
        int buyAmount = 0;

        for (int j = 0; j < appleArray.length; j++) {
            if (categoriesArray[INDEX_OF_SUPPLY].equals(appleArray[j])) {
                supplyAmount += Integer.parseInt(appleArray[j + 1]);
            }
            if (categoriesArray[INDEX_OF_BUY].equals(appleArray[j])) {
                buyAmount += Integer.parseInt(appleArray[j + 1]);
            }
        }

        int resultAmount = supplyAmount - buyAmount;
        StringBuilder reportStringBuilder = new StringBuilder();
        reportStringBuilder.append(getSupplyRowForReport(supplyAmount))
                .append(getBuyRowForReport(buyAmount))
                .append(getResultRowForReport(resultAmount));

        writeToFile(reportStringBuilder, toFileName);
    }

    private String replaceSymbols(String fileContent) {
        return fileContent.replace("\n", LINE_SPLITTER)
                .replace("\r", "")
                .replaceAll(".$", "");
    }

    private String getSupplyRowForReport(int supplyAmount) {
        return SUPPLY_ROW + LINE_SPLITTER
                + supplyAmount + System.lineSeparator();
    }

    private String getBuyRowForReport(int buyAmount) {
        return BUY_ROW + LINE_SPLITTER
                + buyAmount + System.lineSeparator();
    }

    private String getResultRowForReport(int resultAmount) {
        return RESULT_ROW + LINE_SPLITTER
                + resultAmount;
    }

    private void writeToFile(StringBuilder reportStringBuilder, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(reportStringBuilder));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        return stringBuilder.toString();
    }
}
