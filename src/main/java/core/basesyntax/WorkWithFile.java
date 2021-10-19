package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private int valueBuy = 0;
    private int valueSupply = 0;
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String fromFile = fromFileName;
        String toFile = toFileName;

        readingData(fromFile);
        printToFile(valueSupply, valueBuy, result, toFile);
    }

    public void readingData(String fromFileName) {
        File file = new File(fromFileName);

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] values = value.split(",");
                if (values[0].equals("buy")) {
                    valueBuy += Integer.parseInt(values[1]);
                }
                if (values[0].equals("supply")) {
                    valueSupply += Integer.parseInt(values[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Can't read file");
        }
        result = valueSupply - valueBuy;
    }

    public void printToFile(int valueDaySupply, int valueDayBay, int result, String toFileName) {
        File file1 = new File(toFileName);
        try {
            if (file1.delete()) {
                file1.createNewFile();
            }
        } catch (IOException e1) {
            throw new RuntimeException("Can't delete/create file", e1);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(file1, true))) {
            bufferedWriter.write("supply," + valueDaySupply + System.lineSeparator());
            bufferedWriter.write("buy," + valueDayBay + System.lineSeparator());
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }
}
