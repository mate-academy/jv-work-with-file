package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_OPERATION_TYPE = "buy";
    private static final int INDEX_OF_BUY_OPERATION_VOLUME = 0;
    private static final String SUPPLY_OPERATION_TYPE = "supply";
    private static final int INDEX_OF_SUPPLY_OPERATION_VOLUME = 1;
    private static final String REPORT_SEPARATOR = ",";
    private static final String REPORT_RESULT = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        int [] operationsValue = readFromFile(fromFileName);
        String report = createReport(operationsValue);
        writeToFile(toFileName, report);
    }

    public int [] readFromFile(String fromFileName) {
        int buyVolume = 0;
        int supplyVolume = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fromFileName)));) {
            String value;
            while ((value = reader.readLine()) != null) {
                String amount = value.substring(value.lastIndexOf(",") + 1);
                if (value.contains(BUY_OPERATION_TYPE)) {
                    buyVolume += Integer.parseInt(amount);
                }
                if (value.contains(SUPPLY_OPERATION_TYPE)) {
                    supplyVolume += Integer.parseInt(amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return new int[]{buyVolume, supplyVolume};
    }

    public String createReport(int [] operationsValue) {
        return SUPPLY_OPERATION_TYPE + REPORT_SEPARATOR
               + operationsValue[INDEX_OF_SUPPLY_OPERATION_VOLUME] + System.lineSeparator()
               + BUY_OPERATION_TYPE + REPORT_SEPARATOR
               + operationsValue[INDEX_OF_BUY_OPERATION_VOLUME] + System.lineSeparator()
               + REPORT_RESULT + (operationsValue[INDEX_OF_SUPPLY_OPERATION_VOLUME]
                - operationsValue[INDEX_OF_BUY_OPERATION_VOLUME]);
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
