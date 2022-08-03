package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public class WorkWithFile {
    private static final String STRING_BUY = "buy";
    private static final String STRING_SUPPLY = "supply";
    private static final String STRING_СOMA = ",";
    private static final int ZERO = 0;
    private static final int ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fileReader = new File(fromFileName);
        int[] buyAndSupply = new int[2];
        buyAndSupply[ZERO] = ZERO;
        buyAndSupply[ONE] = ZERO;
        buyAndSupply = getSupplyAndBuyFromFile(buyAndSupply, fileReader);
        File fileWriter = new File(toFileName);
        setSupplyAndBuytoFile(buyAndSupply[ZERO], buyAndSupply[ONE], fileWriter);
    }

    private static int[] getSupplyAndBuyFromFile(int[] buyAndSupply, File fileReader) {
        try {
            List<String> lines = Files.readAllLines(fileReader.toPath());
            for (String line : lines) {
                String[] split = line.split(",");
                switch (split[ZERO]) {
                    case STRING_SUPPLY :
                        buyAndSupply[ZERO] += Integer.parseInt(split[ONE]);
                        break;
                    case STRING_BUY :
                        buyAndSupply[ONE] += Integer.parseInt(split[ONE]);
                        break;
                    default:
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
        return buyAndSupply;
    }

    private void setSupplyAndBuytoFile(Integer supply, Integer buy, File fileWriter) {
        try {
            Files.write(fileWriter.toPath(), Collections.singleton(
                    STRING_SUPPLY + STRING_СOMA + supply
                            + System.lineSeparator() + STRING_BUY + STRING_СOMA + buy
                            + System.lineSeparator() + "result" + STRING_СOMA + (supply - buy)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data from the file", e);
        }
    }
}
