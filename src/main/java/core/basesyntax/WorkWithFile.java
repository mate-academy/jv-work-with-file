package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WorkWithFile {
    private ArrayList<DataFromFile> dataList = new ArrayList<>();

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile work = new WorkWithFile();
        work.getDataFile(fromFileName);
        work.writeToFile(toFileName, work.calculating());
    }

    private void getDataFile(String fromFileName) {
        try (FileInputStream filein = new FileInputStream(fromFileName)) {
            if (filein.available() == 0) {
                throw new RuntimeException("File is empty");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(filein));
                String line = null;
                DataFromFile data = null;
                while ((line = br.readLine()) != null) {
                    String[] strings = line.split(",");
                    data = new DataFromFile(strings[0], Integer.valueOf(strings[1]));
                    dataList.add(data);
                }
                br.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
    }

    private int[] calculating() {
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            for (String data : strings) {
                bw.write(data + "\n");
                bw.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
