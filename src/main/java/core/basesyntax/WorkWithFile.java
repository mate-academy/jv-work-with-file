package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY_CONST = "supply";
    private static final String BUY_CONST = "buy";

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
            throw new RuntimeException("Can not write to file" + toFileName);
        }
    }

    private String readFromFile(String fromFileName) {
        StringBuilder startStringBuilder = new StringBuilder();
        try {
            BufferedReader
                    bufferedReader = new BufferedReader(new FileReader(fromFileName));
            int value = bufferedReader.read();

            while (value != -1) {
                startStringBuilder.append((char) value);
                value = bufferedReader.read();
            }
            return startStringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can not read from file " + fromFileName);
        }
    }
    
    private String createReport(String dataFromFile) {
        String[] tmpArray;
        int supplySum = 0;
        int buySum = 0;

        tmpArray = dataFromFile.split(",|\\n");

        for (int i = 0;i < tmpArray.length;i++) {
            if (tmpArray[i].equals(SUPPLY_CONST)) {
                supplySum += Integer.parseInt(tmpArray[i + 1]);
            }

            if (tmpArray[i].equals(BUY_CONST)) {
                buySum += Integer.parseInt(tmpArray[i + 1]);
            }
        }

        return SUPPLY_CONST + "," + supplySum + System.lineSeparator()
                + BUY_CONST + "," + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum);
    }
}
