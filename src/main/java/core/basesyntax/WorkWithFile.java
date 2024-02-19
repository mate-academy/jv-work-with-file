package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int BUY_OR_SUPPLY_INDEX = 1;
    private static final int BUY_ARRAY_INDEX = 0;
    private static final int SUPPLY_ARRAY_INDEX = 1;
    private static final int BUY_AND_SUPPLY_ARRAY_SIZE = 2;
    private static final String BUY = "buy,";
    private static final String SUPPLY = "supply,";
    private static final String RESULT = "result,";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder buyAndSupplyString = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String lineOfFileName = bufferedReader.readLine();
            buyAndSupplyString.append(lineOfFileName);
            while (lineOfFileName != null) {
                lineOfFileName = bufferedReader.readLine();
                if (lineOfFileName == null) {
                    break;
                }
                buyAndSupplyString.append(System.lineSeparator()).append(lineOfFileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        return buyAndSupplyString.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writeToFileName = new BufferedWriter(new FileWriter(toFileName))) {
            writeToFileName.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

    private int parseBuyOrSupply(String lineOfFileName) {
        return Integer.parseInt(lineOfFileName.split(SEPARATOR)[BUY_OR_SUPPLY_INDEX]);
    }

    private String createReport(String buyAndSupplyString) {
        int bye = 0;
        int supply = 0;
        String[] buyAndSupplyArray = buyAndSupplyString.split(System.lineSeparator());
        for (String buyOrSupply : buyAndSupplyArray) {
            if (buyOrSupply.contains(BUY)) {
                bye += parseBuyOrSupply(buyOrSupply);
            } else {
                supply += parseBuyOrSupply(buyOrSupply);
            }
        }
        return SUPPLY + supply + System.lineSeparator()
                + BUY + bye + System.lineSeparator() + RESULT + (supply - bye);
    }
}
