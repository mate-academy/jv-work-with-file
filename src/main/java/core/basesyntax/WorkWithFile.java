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
        File fromFile = new File(fromFileName);
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String readDataFromFile = "";
            while (readDataFromFile != null) {
                readDataFromFile = bufferedReader.readLine();
                if (readDataFromFile != null) {
                    int valueAfterComma = Integer.parseInt(
                            readDataFromFile.substring(readDataFromFile.indexOf(COMMA) + 1));
                    if (readDataFromFile.startsWith(SUPPLY)) {
                        sumOfSupply += valueAfterComma;
                    }
                    if (readDataFromFile.startsWith(BUY)) {
                        sumOfBuy += valueAfterComma;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        String report = createReport(sumOfSupply, sumOfBuy);
        writeToFile(toFileName, report);
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
            createFile(toFileName);
            try {
                Files.write(toFile.toPath(), dataWriteToFile.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file: " + toFileName, e);
            }
        }
    }

    public static void createFile(String toFileName) {
        File toFile = new File(toFileName);
        try {
            toFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file: " + toFileName, e);
        }
    }
}
