package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_AMOUNT_INDEX = 0;
    private static final int BUY_AMOUNT_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private int[] readFile(String fromFileName) {
        int supplyAmount = getSupplyAmount(fromFileName);
        int buyAmount = getBuyAmount(fromFileName);
        int result = supplyAmount - buyAmount;
        return new int[] {supplyAmount, buyAmount, result};
    }

    private int getBuyAmount(String fromFileName) {
        int buyAmount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] data = currentLine.split(",");
                if (data[OPERATION_TYPE_INDEX].equals(OPERATION_BUY)) {
                    buyAmount += Integer.parseInt(data[AMOUNT_INDEX]);
                }
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Сan't read this file properly.", e);
        }
        return buyAmount;
    }

    private int getSupplyAmount(String fromFileName) {
        int supplyAmount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] data = currentLine.split(",");
                if (data[OPERATION_TYPE_INDEX].equals(OPERATION_SUPPLY)) {
                    supplyAmount += Integer.parseInt(data[AMOUNT_INDEX]);
                }
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Сan't read this file properly.", e);
        }
        return supplyAmount;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Сan't write to this file properly.", e);
        }
    }

    private String createReport(int[] dataFromFile) {
        StringBuilder report = new StringBuilder();
        report.append("supply,")
                .append(dataFromFile[SUPPLY_AMOUNT_INDEX])
                .append(System.lineSeparator())
                .append("buy,").append(dataFromFile[BUY_AMOUNT_INDEX])
                .append(System.lineSeparator())
                .append("result,").append(dataFromFile[RESULT_INDEX]);
        System.out.println(report.toString());
        return report.toString();
    }
}
