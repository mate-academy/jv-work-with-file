package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";

    private int bought;
    private int supplied;
    private int resultNumber;

    public void getStatistic(String fromFileName, String toFileName) {
        createFile(toFileName);

        readFile(fromFileName);

        writeFile(toFileName);
    }

    public void createFile(String fileName) {
        File toFile = new File(fileName);

        try {
            toFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }

    public void readFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String inputLine = bufferedReader.readLine();

            bought = 0;
            supplied = 0;

            while (inputLine != null) {
                String[] splitLine = inputLine.split(",");

                if (splitLine[0].equals(SUPPLY_OPERATION)) {
                    supplied = Integer.parseInt(splitLine[1]) + supplied;
                } else {
                    bought = Integer.parseInt(splitLine[1]) + bought;
                }

                inputLine = bufferedReader.readLine();
            }

            resultNumber = supplied - bought;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public void writeFile(String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(SUPPLY_OPERATION + "," + supplied);
            bufferedWriter.newLine();

            bufferedWriter.write(BUY_OPERATION + "," + bought);
            bufferedWriter.newLine();

            bufferedWriter.write(RESULT_OPERATION + "," + resultNumber);
            bufferedWriter.newLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
