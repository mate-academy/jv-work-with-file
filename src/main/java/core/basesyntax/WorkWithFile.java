package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String RESULT = "result";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(calculateData(readFromFile(fromFileName)), toFileName);

    }

    private String[] readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String valueForReading = reader.readLine();
            while (valueForReading != null) {
                builder.append(valueForReading).append(System.lineSeparator());
                valueForReading = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fromFileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private void writeToFile(int[] data, String fileName) {
        File file = new File(fileName);
        try {
            //file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            writer.write(
                    OPERATION_SUPPLY + SEPARATOR + data[SUPPLY_INDEX] + System.lineSeparator()
                       + OPERATION_BUY + SEPARATOR + data[BUY_INDEX] + System.lineSeparator()
                            + RESULT + SEPARATOR + data[RESULT_INDEX] + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file " + fileName + e);
        }
    }

    private int[] calculateData(String[] data) {
        int separatorIndex;
        int supplyTotal = 0;
        int buyTotal = 0;
        for (String dataString: data) {
            separatorIndex = dataString.indexOf(SEPARATOR);
            int var = Integer.parseInt(dataString.substring(separatorIndex + 1));
            if (dataString.contains(OPERATION_SUPPLY)) {
                supplyTotal += var;
            } else {
                if (dataString.contains(OPERATION_BUY)) {
                    buyTotal += var;
                } else {
                    throw new RuntimeException("Data`s format isn`t correct");
                }
            }
        }
        return new int[]{supplyTotal, buyTotal, supplyTotal - buyTotal};
    }

}
