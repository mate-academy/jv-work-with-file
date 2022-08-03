package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<DataFromFile> dataList = new ArrayList<>();
        getDataFile(fromFileName, dataList);
        writeToFile(toFileName, calculating(dataList));
    }

    private void getDataFile(String fromFileName, ArrayList<DataFromFile> dataList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = null;
            DataFromFile data = null;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                data = new DataFromFile(strings[0], Integer.valueOf(strings[1]));
                dataList.add(data);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
    }

    private int[] calculating(ArrayList<DataFromFile> dataList) {
        int[] sumOfResults = new int[3];
        for (DataFromFile data : dataList) {
            if (data.getOperationType().equals("supply")) {
                sumOfResults[0] += data.getAmount();
            }
            if (data.getOperationType().equals("buy")) {
                sumOfResults[1] += data.getAmount();
            }
        }
        sumOfResults[2] = sumOfResults[0] - sumOfResults[1];
        return sumOfResults;
    }

    private void writeToFile(String toFileName, int[] sumOfResults) {
        String[] strings = new String[]{"supply," + sumOfResults[0], "buy,"
                + sumOfResults[1], "result," + sumOfResults[2]};
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (String data : strings) {
                writer.write(data + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
