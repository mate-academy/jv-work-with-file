package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String RESULT = "result";
    private static final int DATA_ARRAY_SIZE = 3;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(toFileName, formReport(processData(readDataFromFile(fromFileName))));
    }

    private String[] readDataFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(new File(fileName)))) {
            String dataString = bufferedReader.readLine();
            while (dataString != null) {
                stringBuilder.append(dataString).append(System.lineSeparator());
                dataString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read from file" + fileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String formReport(int[] data) {
        return OPERATION_SUPPLY + SEPARATOR + data[SUPPLY_INDEX] + System.lineSeparator()
                + OPERATION_BUY + SEPARATOR + data[BUY_INDEX] + System.lineSeparator()
                + RESULT + SEPARATOR + data[RESULT_INDEX] + System.lineSeparator();
    }

    private void writeDataToFile(String fileName, String report) {
        try {
            File file = new File(fileName);
            file.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            bufferedWriter.write(report);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file " + fileName, e);
        }
    }

    private int[] processData(String[] data) {
        int separatorIndex;
        int supplyTotal = 0;
        int buyTotal = 0;
        for (String dataString : data) {
            separatorIndex = dataString.indexOf(SEPARATOR);
            if (dataString.contains(OPERATION_SUPPLY)) {
                supplyTotal += Integer.parseInt(dataString.substring(separatorIndex + 1));
            } else if (dataString.contains(OPERATION_BUY)) {
                buyTotal += Integer.parseInt(dataString.substring(separatorIndex + 1));
            } else {
                throw new RuntimeException("Invalid data format");
            }
        }
        return new int[]{supplyTotal, buyTotal, supplyTotal - buyTotal};
    }
}
