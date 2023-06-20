package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readFile = readFile(fromFileName);
        String report = createReport(readFile);
        recordInFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        File file = new File(fromFileName);
        String[] arrayOfData = new String[0];
        String dataFromFile;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            dataFromFile = reader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while (dataFromFile != null) {
                stringBuilder.append(dataFromFile).append(",");
                dataFromFile = reader.readLine();
                arrayOfData = stringBuilder.toString().split(",");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return arrayOfData;
    }

    private String createReport(String[] arrayOfData) {
        int supplySumm = 0;
        int buySumm = 0;
        for (int i = 0; i < arrayOfData.length; i++) {
            if (arrayOfData[i].equals("supply")) {
                supplySumm += Integer.parseInt(arrayOfData[i + 1]);
            } else if (arrayOfData[i].equals("buy")) {
                buySumm += Integer.parseInt(arrayOfData[i + 1]);
            }
        }
        int result = supplySumm - buySumm;
        final StringBuilder shapeBuilder = new StringBuilder();
        shapeBuilder.append("supply," + supplySumm + System.lineSeparator())
                .append("buy," + buySumm + System.lineSeparator())
                .append("result," + result);
        return shapeBuilder.toString();
    }

    private void recordInFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}

