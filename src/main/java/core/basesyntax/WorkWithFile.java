package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final char COMMA = ',';
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = dataCalculation(fromFileName);
        writeToFile(toFileName, report);
    }

    public static String dataCalculation(String fromFileName) {
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readDataFromFile = "";
            String[] dataLine;
            while (readDataFromFile != null) {
                readDataFromFile = bufferedReader.readLine();
                if (readDataFromFile != null) {
                    dataLine = readDataFromFile.split(",");
                    if (dataLine[0].equals(SUPPLY)) {
                        sumOfSupply += Integer.parseInt(dataLine[1]);
                    }
                    if (dataLine[0].equals(BUY)) {
                        sumOfBuy += Integer.parseInt(dataLine[1]);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return createReport(sumOfSupply, sumOfBuy);

    }

    private static String createReport(int sumOfSupply, int sumOfBuy) {
        String dataWriteToFile = SUPPLY + COMMA + sumOfSupply + System.lineSeparator()
                + BUY + COMMA + sumOfBuy + System.lineSeparator()
                + RESULT + COMMA + (sumOfSupply - sumOfBuy);
        return dataWriteToFile;
    }

    public static void writeToFile(String toFileName, String dataWriteToFile) {
        File toFile = new File(toFileName);
        if (!toFile.exists()) {
            try {
                toFile.createNewFile();
                Files.write(toFile.toPath(), dataWriteToFile.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file: " + toFileName, e);
            }
        }
    }
}
