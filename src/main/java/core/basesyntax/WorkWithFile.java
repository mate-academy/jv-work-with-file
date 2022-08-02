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
    private int supply = 0;
    private int buy = 0;
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile work = new WorkWithFile();
        work.getDataFile(fromFileName);
        result = work.calculating();
        work.writeToFile(toFileName, result);
    }

    public void getDataFile(String fromFileName) {
        FileInputStream filein;
        try {
            filein = new FileInputStream(fromFileName);
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
                    data = null;
                }
                filein.close();
                br.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
    }

    public int calculating() {
        for (DataFromFile data : dataList) {
            if (data.getOperationType().equals("supply")) {
                supply += data.getAmount();
            }
            if (data.getOperationType().equals("buy")) {
                buy += data.getAmount();
            }
        }
        return result = supply - buy;
    }

    public void writeToFile(String toFileName, int result) {
        String[] strings = new String[]{"supply," + supply, "buy," + buy, "result," + result};
        File file = new File(toFileName);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, true));
            for (String data : strings) {
                bw.write(data + "\n");
                bw.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter", e);
                }
            }
        }
    }
}
