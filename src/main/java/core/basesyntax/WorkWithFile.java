package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        int[] data = workWithFile.readFromFile(fromFileName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("supply,").append(data[0])
                    .append(System.lineSeparator())
                    .append("buy,").append(data[1])
                    .append(System.lineSeparator())
                    .append("result,").append(data[0] - data[1]);
        workWithFile.writeToFile(stringBuffer.toString(), toFileName);
    }

    private int[] readFromFile(String fileName) {
        int supply = 0;
        int buy = 0;
        File file = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String row = bufferedReader.readLine();
            while (row != null) {
                String[] data = row.split(",");
                if (data[0].equals("supply")) {
                    supply += Integer.parseInt(data[1]);
                } else {
                    buy += Integer.parseInt(data[1]);
                }
                row = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName, e);
        }
        return new int[]{supply, buy};
    }

    private void writeToFile(String report, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + fileName, e);
        }
    }
}
