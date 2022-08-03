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
        File fileWriter = new File(toFileName);
        try {
            List<String> lines = Files.readAllLines(fileReader.toPath());
            int buy = ZERO;
            int supply = ZERO;
            for (String line : lines) {
                switch (line.substring(ZERO, line.indexOf(STRING_СOMA))) {
                    case STRING_SUPPLY :
                        supply += Integer.parseInt(line.substring(line.indexOf(STRING_СOMA) + ONE));
                        break;
                    case STRING_BUY :
                        buy += Integer.parseInt(line.substring(line.indexOf(STRING_СOMA) + ONE));
                        break;
                    default:
                }
            }
            Files.write(fileWriter.toPath(), Collections.singleton(
                    STRING_SUPPLY + STRING_СOMA + supply
                    + System.lineSeparator() + STRING_BUY + STRING_СOMA + buy
                    + System.lineSeparator() + "result" + STRING_СOMA + (supply - buy)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
