package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String report = readFile(fromFileName);
        writeFile(toFileName, report);
    }

    private String readFile(String fromFileNameData) {
        final String supply = "supply";
        final String buy = "buy";
        int supplySum = 0;
        int buySum = 0;
        int result = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileNameData))) {
            String value = bufferedReader.readLine();

            while (value != null) {
                String[] oneLine;
                oneLine = value.split(",");
                if (oneLine[0].equals(supply)) {
                    supplySum += Integer.parseInt(oneLine[1]);
                } else if (oneLine[0].equals(buy)) {
                    buySum += Integer.parseInt(oneLine[1]);
                } else {
                    throw new RuntimeException("Unknown operation");
                }
                value = bufferedReader.readLine();
            }
            result = supplySum - buySum;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileNameData, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read line", e);
        }
        String stringFinal = supply + "," + supplySum + System.lineSeparator() + buy
                + "," + buySum + System.lineSeparator() + "result" + "," + result;

        return stringFinal;
    }

    private void writeFile(String filePath, String fileContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(fileContent);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data in file", e);
        }
    }
}
