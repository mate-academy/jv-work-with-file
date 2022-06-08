package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMA = ",";
    private static final String SUPPLY_TITLE = "supply";
    private static final String BUY_TITLE = "buy";
    private static final String RESULT_TITLE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(fromFileName);
        try {
            resultLine(writeFile(fromFileName),toFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private BufferedReader writeFile(String fromFileName) {
        File srcFile = new File(fromFileName);
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(srcFile));

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return reader;
    }

    private void resultLine(BufferedReader reader,String toFileName) throws IOException {
        int supplyAmount = 0;
        int buyAmount = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            String[] column = line.split(COMA);
            if (column.length != 2) {
                throw new RuntimeException("Incorrect column count in '" + line + "'");
            }
            switch (column[0]) {
                case SUPPLY_TITLE: {
                    supplyAmount += Integer.parseInt(column[1]);
                    break;
                }
                case BUY_TITLE: {
                    buyAmount += Integer.parseInt(column[1]);
                    break;
                }
                default:
                    throw new RuntimeException("Incorrect title '"
                            + column[0] + "' in '" + line + "'");
            }
        }

        saveStatistic(supplyAmount, buyAmount, toFileName);
    }

    private void saveStatistic(int supplyAmount, int buyAmount, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY_TITLE + COMA + supplyAmount + System.lineSeparator());
            writer.write(BUY_TITLE + COMA + buyAmount + System.lineSeparator());
            writer.write(RESULT_TITLE + COMA + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write file" + toFileName, e);
        }
    }
}
