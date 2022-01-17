package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supplyAll = 0;
    private int buyAll = 0;
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] array = line.split(",");
                if (array[0].equals("supply")) {
                    supplyAll += Integer.parseInt(array[1]);
                } else {
                    buyAll += Integer.parseInt(array[1]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file" + fromFileName, e);
        }
        result = supplyAll - buyAll;
        writeStatisticToFile(toFileName);
    }

    public void writeStatisticToFile(String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create the file" + toFileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("supply," + String.valueOf(supplyAll));
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + String.valueOf(buyAll));
            bufferedWriter.newLine();
            bufferedWriter.write("result," + String.valueOf(result));
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException("can't write to a file", e);
        }
    }
}
