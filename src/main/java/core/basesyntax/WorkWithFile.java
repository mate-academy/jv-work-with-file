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

        int supply = 0;
        int buy = 0;
        for (DataFromFile data : dataList) {
            if (data.getOperationType().equals("supply")) {
                supply += data.getAmount();
            }
            if (data.getOperationType().equals("buy")) {
                buy += data.getAmount();
            }
        }
        int result = supply - buy;
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
