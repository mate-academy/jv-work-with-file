package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUM_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        List<DataFromFile> dataList = new ArrayList<>();
        getDataFromFile(fromFileName, dataList);
        writeToFile(toFileName, calculating(dataList));
    }

    private void getDataFromFile(String fromFileName, List<DataFromFile> dataList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = null;
            DataFromFile data = null;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                data = new DataFromFile(strings[OPERATION_INDEX],
                        Integer.valueOf(strings[AMOUNT_INDEX]));
                dataList.add(data);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
    }

    private int[] calculating(List<DataFromFile> dataList) {
        int[] sumOfResults = new int[3];
        for (DataFromFile data : dataList) {
            if (data.getOperationType().equals("supply")) {
                sumOfResults[OPERATION_INDEX] += data.getAmount();
            }
            if (data.getOperationType().equals("buy")) {
                sumOfResults[AMOUNT_INDEX] += data.getAmount();
            }
        }
        sumOfResults[SUM_INDEX] = sumOfResults[OPERATION_INDEX] - sumOfResults[AMOUNT_INDEX];
        return sumOfResults;
    }

    private void writeToFile(String toFileName, int[] sumOfResults) {
        String[] strings = new String[]{"supply," + sumOfResults[OPERATION_INDEX], "buy,"
                + sumOfResults[AMOUNT_INDEX], "result," + sumOfResults[SUM_INDEX]};
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (String data : strings) {
                writer.write(data + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
