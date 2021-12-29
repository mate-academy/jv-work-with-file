package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String operationSupply = "supply";
    private final String operationBuy = "buy";
    private int countSupply = 0;
    private int countBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readerFile(fromFileName);
        String[] resultOperation = {operationSupply + "," + Integer.toString(countSupply),
                                    operationBuy + "," + Integer.toString(countBuy),
                                    resultOperation(countSupply, countBuy)};
        writeFile(resultOperation, toFileName);
    }

    private void readerFile(String fromFileName) {
        File fromFile = new File(fromFileName);

        try (BufferedReader csvReader =
                     new BufferedReader(new FileReader(String.valueOf(fromFile.toPath())))) {
            String value = csvReader.readLine();
            while (value != null) {
                calculateOperation(value);
                value = csvReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    private void calculateOperation(String line) {
        String [] splitLine = line.split(",");
        if (splitLine[0].equals(operationSupply)) {
            countSupply += Integer.parseInt(splitLine[1]);
        } else {
            countBuy += Integer.parseInt(splitLine[1]);
        }
    }

    private String resultOperation(int countSupply, int countBuy) {
        return "result," + Integer.toString(countSupply - countBuy);
    }

    private void writeFile(String[] data, String toFile) {
        File file = new File(toFile);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create the file", e);
        }
        for (String line : data) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
                bufferedWriter.write(line + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
