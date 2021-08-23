package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        File tofile = createFile(toFileName);
        String[] arr = getDataFromFile(fromFileName).split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        StringBuilder report = new StringBuilder();
        for (String line : arr) {
            String name = line.substring(0, line.indexOf(','));
            int value = Integer.parseInt(line.substring(line.indexOf(',') + 1));
            if (SUPPLY.equals(name)) {
                supplyAmount += value;
            }
            if (BUY.equals(name)) {
                buyAmount += value;
            }
        }
        report.append("supply,")
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyAmount)
                .append(System.lineSeparator())
                .append("result")
                .append(",").append(supplyAmount - buyAmount);

        writeTofile(toFileName, report.toString());

    }

    private File createFile(String fileName) {
        File toFile;
        try {
            toFile = new File(fileName);
            toFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot create file.", e);
        }
        return toFile;
    }

    private String getDataFromFile(String fileName) {
        StringBuilder fileContant = new StringBuilder();
        try {
            BufferedReader fromFile = new BufferedReader(new FileReader(fileName));
            String line = fromFile.readLine();
            while (line != null) {
                fileContant.append(line).append(System.lineSeparator());
                line = fromFile.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }
        return fileContant.toString();
    }

    private void writeTofile(String fileNmae, String data) {
        BufferedWriter toFile;
        try {
            toFile = new BufferedWriter(new FileWriter(fileNmae));
            toFile.write(data);
            toFile.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to file", e);
        }
    }
}
