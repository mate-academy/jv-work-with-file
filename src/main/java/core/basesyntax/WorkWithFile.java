package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String report = getReport(fromFileName);
        writeStatisticToFile(report, toFileName);
    }

    private String getReport(String fromFileName) {
        int supplyAll = 0;
        int buyAll = 0;
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
        int result = supplyAll - buyAll;
        return "supply," + supplyAll + System.lineSeparator() + "buy," + buyAll
                + System.lineSeparator() + "result," + result;
    }

    private void writeStatisticToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create the file" + toFileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("can't write to a file", e);
        }
    }
}
