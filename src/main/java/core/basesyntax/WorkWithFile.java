package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static int SUPPLY_INDEX = 0;
    private static int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String oneLine = reader.readLine();
            while (oneLine != null) {
                String[] fileInfo = oneLine.split(",");
                if (fileInfo[SUPPLY_INDEX].equals("supply")) {
                    supplySum += Integer.parseInt(fileInfo[AMOUNT_INDEX]);
                } else {
                    buySum += Integer.parseInt(fileInfo[AMOUNT_INDEX]);
                }
                oneLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        getInfoInFile(supplySum, buySum, toFileName);
    }

    private void getInfoInFile(int supplySum, int buySum, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplySum + System.lineSeparator());
            writer.write("buy," + buySum + System.lineSeparator());
            writer.write("result," + (supplySum - buySum) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Cant write info in file", e);
        }
    }
}
