package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String SPLIT_CHAR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] supplyAndBuyResult = new int[2];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitedLine = line.split(SPLIT_CHAR);
                if (splitedLine[OPERATION_INDEX].equals("supply")) {
                    supplyAndBuyResult [0] += Integer.parseInt(splitedLine[AMMOUNT_INDEX]);
                } else {
                    supplyAndBuyResult [1] += Integer.parseInt(splitedLine[AMMOUNT_INDEX]);
                }
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        writeToFile(createReport(supplyAndBuyResult), toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String createReport(int[] supplyAndBuyResult) {
        return new StringBuilder()
                .append("supply,")
                .append(supplyAndBuyResult[SUPPLY_INDEX])
                .append(System.lineSeparator())
                .append("buy,")
                .append(supplyAndBuyResult[BUY_INDEX])
                .append(System.lineSeparator())
                .append("result,")
                .append(supplyAndBuyResult [SUPPLY_INDEX] - supplyAndBuyResult [BUY_INDEX])
                .append(System.lineSeparator()).toString();
    }
}
