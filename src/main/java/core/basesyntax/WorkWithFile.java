package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY_ROW = "supply";
    private static final int INDEX_OF_SUPPLY = 0;
    private static final String BUY_ROW = "buy";
    private static final int INDEX_OF_BUY = 1;
    private static final String RESULT_ROW = "result";
    private static final String LINE_SPLITTER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent;
        try {
            fileContent = Files.readString(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
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

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportToString(reportStringBuilder));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

    public String replaceSymbols(String fileContent) {
        return fileContent.replace("\n", LINE_SPLITTER)
                .replace("\r", "")
                .replaceAll(".$", "");
    }

    public String getSupplyRowForReport(int supplyAmount) {
        return SUPPLY_ROW + LINE_SPLITTER
                + supplyAmount + System.lineSeparator();
    }

    public String getBuyRowForReport(int buyAmount) {
        return BUY_ROW + LINE_SPLITTER
                + buyAmount + System.lineSeparator();
    }

    public String getResultRowForReport(int resultAmount) {
        return RESULT_ROW + LINE_SPLITTER
                + resultAmount;
    }

    public String reportToString(StringBuilder reportStringBuilder) {
        return String.valueOf(reportStringBuilder);
    }
}
