package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        int totalSupplyAmount = 0;
        int totalBuyAmount = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFile))) {
            String lineFromFile;

            while ((lineFromFile = fileReader.readLine()) != null) {
                String[] elements = lineFromFile.split(",");

                if (elements[0].equals("supply")) {
                    totalSupplyAmount += Integer.parseInt(elements[1]);
                } else if (elements[0].equals("buy")) {
                    totalBuyAmount += Integer.parseInt(elements[1]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file" + e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e);
        }

        File outputFile = new File(toFileName);
        try {
            if (outputFile.exists()) {
                outputFile.delete();
            }
            outputFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file" + e);
        }

        try (BufferedWriter outputFileWriter = new BufferedWriter(new FileWriter(outputFile))) {
            outputFileWriter.write("supply," + totalSupplyAmount + System.lineSeparator());
            outputFileWriter.write("buy," + totalBuyAmount + System.lineSeparator());
            outputFileWriter.write("result," + (totalSupplyAmount - totalBuyAmount)
                    + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + e);
        }
    }
}
