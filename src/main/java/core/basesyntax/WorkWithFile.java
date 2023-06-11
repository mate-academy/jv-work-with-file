package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file1 = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] fileInfo = stringBuilder.toString().replace("\r\n", " ").split(" ");
        int supplySum = 0;
        int buySum = 0;
        for (int k = 0; k < fileInfo.length; k++) {
            String[] stringInfo = fileInfo[k].split(",");
            if (stringInfo[0].equals("supply")) {
                supplySum += Integer.parseInt(stringInfo[1]);
            } else {
                buySum += Integer.parseInt(stringInfo[1]);
            }
        }
        String secondFileInfo = "supply," + supplySum + "\r\n" + "buy,"
                + buySum + "\r\n" + "result," + (supplySum - buySum) + "\r\n";
        File file2 = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2))) {
            bufferedWriter.write(secondFileInfo);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

}
