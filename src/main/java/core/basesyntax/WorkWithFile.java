package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TYPE_OF_OPERATION_INDEX = 0;
    private static final int COST_INDEX = 1;
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String RESULT_WORD = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File from = new File(fromFileName);
        int sumSupply = 0;
        int sumBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(from))) {
            String note = bufferedReader.readLine();
            while (note != null) {
                String[] supplier = note.split(",");
                if (supplier[TYPE_OF_OPERATION_INDEX].equals(SUPPLY_WORD)) {
                    sumSupply += Integer.parseInt(supplier[COST_INDEX]);
                } else if (supplier[TYPE_OF_OPERATION_INDEX].equals(BUY_WORD)) {
                    sumBuy += Integer.parseInt(supplier[COST_INDEX]);
                }
                note = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file", e);
        }
        writeToFile(toFileName, sumSupply, sumBuy);
    }

    private void writeToFile(String fileName, int sumSupply, int sumBuy) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(SUPPLY_WORD + "," + sumSupply + System.lineSeparator()
                    + BUY_WORD + "," + sumBuy + System.lineSeparator()
                    + RESULT_WORD + "," + (sumSupply - sumBuy));
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file", e);
        }
    }
}
