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
        File fromFile = new File(fromFileName);
        String sumSupply = null;
        String sumBuy = null;

        try (BufferedReader csvReader =
                     new BufferedReader(new FileReader(String.valueOf(fromFile.toPath())))) {
            String value = csvReader.readLine();
            while (value != null) {
                sumSupply = calculateSupply(value);
                sumBuy = calculateBuy(value);
                System.out.println(value);
                value = csvReader.readLine();
            }
            createFile(toFileName);
            String[] resultOperation = {sumSupply, sumBuy, resultOperation(countSupply, countBuy)};
            writeFile(resultOperation, toFileName);
            System.out.println(sumBuy);
            System.out.println(sumSupply);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    private String calculateSupply(String line) {
        String [] splitSupply = line.split(",");
        if (splitSupply[0].equals(operationSupply)) {
            countSupply += Integer.parseInt(splitSupply[1]);
        }
        return operationSupply + "," + Integer.toString(countSupply);
    }

    private String calculateBuy(String line) {
        String [] splitBuy = line.split(",");
        if (splitBuy[0].equals(operationBuy)) {
            countBuy += Integer.parseInt(splitBuy[1]);
        }
        return operationBuy + "," + Integer.toString(countBuy);
    }

    private void createFile(String name) {
        File toFile = new File(name);
        try {
            toFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create the file", e);
        }
    }

    private String resultOperation(int countSupply, int countBuy) {
        return "result," + Integer.toString(countSupply - countBuy);
    }

    private void writeFile(String[] data, String toFile) {
        for (String line : data) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
                bufferedWriter.write(line + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
