package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String reportString, String toFileName) {
        try {
            PrintWriter writer = new PrintWriter(toFileName);
            writer.print("");
            writer.close();

            new File(toFileName).createNewFile();
            Files.write(new File(toFileName).toPath(), reportString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file 'toFileName'");
        }
    }

    private String readFromFile(String fromFileName) {
        StringBuilder startStringBuilder = new StringBuilder();
        try {
            BufferedReader
                    bufferedReader = new BufferedReader(new FileReader(new File(fromFileName)));
            int value = bufferedReader.read();

            while (value != -1) {
                startStringBuilder.append((char) value);
                value = bufferedReader.read();
            }
            return startStringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can not read from file 'fromFileName'");
        }
    }
    
    private String createReport(String dataFromFile) {
        String[] tmpArray;
        int supplySum = 0;
        int buySum = 0;

        tmpArray = new String[dataFromFile.length()];
        tmpArray = dataFromFile.split(",|\\n");

        for (int i = 0;i < tmpArray.length;i++) {
            if (tmpArray[i].equals("supply")) {
                supplySum += Integer.parseInt(tmpArray[i + 1]);
            }

            if (tmpArray[i].equals("buy")) {
                buySum += Integer.parseInt(tmpArray[i + 1]);
            }
        }

        String reportString;

        reportString = "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum);

        return reportString;
    }
}
