package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int WORD_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int result = 0;
        int amountOfSupply = 0;
        int amountOfBuy = 0;
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String originalString = bufferedReader.readLine();
            while (originalString != null) {
                System.out.println(originalString);
                String[] splited = originalString.split(",");
                if (splited.length != 2) {
                    throw new RuntimeException("Problem with data format" + originalString);
                }
                if (splited[WORD_INDEX].equals("supply")) {
                    amountOfSupply += Integer.parseInt(splited[AMOUNT_INDEX]);
                } else if (splited[WORD_INDEX].equals("buy")) {
                    amountOfBuy += Integer.parseInt(splited[AMOUNT_INDEX]);
                } else {
                    throw new RuntimeException("Wrong data format " + splited[WORD_INDEX]);
                }
                originalString = bufferedReader.readLine();
            }
            result = amountOfSupply - amountOfBuy;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + e);
        }
        File fileToWrite = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {
            bufferedWriter.write("supply," + amountOfSupply + System.lineSeparator());
            bufferedWriter.write("buy," + amountOfBuy + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write any information to the file! " + e);
        }
    }
}
