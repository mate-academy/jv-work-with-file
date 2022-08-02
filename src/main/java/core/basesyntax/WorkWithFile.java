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
        fileContent = fileContent.replace("\n", LINE_SPLITTER)
                .replace("\r", "")
                .replaceAll(".$", "");
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

        int resultAmmount = supplyAmount - buyAmount;
        String supplyRowForReport = SUPPLY_ROW + LINE_SPLITTER
                + supplyAmount + System.lineSeparator();
        String buyRowForReport = BUY_ROW + LINE_SPLITTER
                + buyAmount + System.lineSeparator();
        String resultRowForReport = RESULT_ROW + LINE_SPLITTER
                + resultAmmount;
        StringBuilder reportStringbuilder = new StringBuilder();
        reportStringbuilder.append(supplyRowForReport)
                .append(buyRowForReport)
                .append(resultRowForReport);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(reportStringbuilder));
        } catch (Exception e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
