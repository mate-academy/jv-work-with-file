package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFile(fromFileName);
        String report = createReport(data);
        writeFile(toFileName, report);
    }

    private int[] readFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String inputLine = bufferedReader.readLine();

            int bought = 0;
            int supplied = 0;

            while (inputLine != null) {
                String[] splitLine = inputLine.split(",");

                if (splitLine[0].equals(SUPPLY_OPERATION)) {
                    supplied = Integer.parseInt(splitLine[1]) + supplied;
                } else {
                    bought = Integer.parseInt(splitLine[1]) + bought;
                }

                inputLine = bufferedReader.readLine();
            }

            int resultNumber = supplied - bought;
            return new int[]{supplied, bought, resultNumber};
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file" + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
    }

    private void writeFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            String[] array = data.split(" ");

            int supplied = Integer.parseInt(array[0]);
            int bought = Integer.parseInt(array[1]);
            int resultNumber = Integer.parseInt(array[2]);

            bufferedWriter.write(SUPPLY_OPERATION + "," + supplied);
            bufferedWriter.newLine();

            bufferedWriter.write(BUY_OPERATION + "," + bought);
            bufferedWriter.newLine();

            bufferedWriter.write(RESULT_OPERATION + "," + resultNumber);
            bufferedWriter.newLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file" + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }

    private String createReport(int[] data) {
        return String.valueOf(data[0]) + " " + String.valueOf(data[1]) + " "
                + String.valueOf(data[2]);
    }
}
