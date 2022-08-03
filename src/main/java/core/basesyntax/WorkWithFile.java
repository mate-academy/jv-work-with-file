package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int sumOfBuy = 0;
        int sumOfSupply = 0;
        int result = 0;

        StringBuilder builder = new StringBuilder();
        readFromFile(fromFileName, builder);
        String[] textParts = builder.toString().split(DELIMITER);
        for (int i = 0; i < textParts.length; i++) {
            if (textParts[i].equals(SUPPLY)) {
                sumOfSupply += Integer.parseInt(textParts[i + 1]);
            }
            if (textParts[i].equals(BUY)) {
                sumOfBuy += Integer.parseInt(textParts[i + 1]);
            }
            result = sumOfSupply - sumOfBuy;
        }
        write(toFileName, sumOfSupply, sumOfBuy, result);
    }

    private void readFromFile(String fromFileName,
                                    StringBuilder builder) {
        File fileName = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(DELIMITER);
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can not find the file" + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can not read the file" + fileName, e);
        }
    }

    private String [] createReport(int sumOfSupply, int sumOfBuy, int result) {
        String[] array = new String[3];
        array[0] = SUPPLY + DELIMITER + sumOfSupply;
        array[1] = BUY + DELIMITER + sumOfBuy;
        array[2] = RESULT + DELIMITER + result;
        return array;
    }

    private void write(String toFileName, int sumOfSupply, int sumOfBuy, int result) {
        File outputFile = new File(toFileName);
        String[] array = createReport(sumOfSupply, sumOfBuy, result);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile,
                true))) {
            for (String element : array) {
                bufferedWriter.write(element + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not find the file" + outputFile, e);
        }
    }
}
