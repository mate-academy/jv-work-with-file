package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int ACTION_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] sourceStrings;
        File fileFrom = new File(fromFileName);
        sourceStrings = readFile(fileFrom).split(" ");
        int[] results = new int[sourceStrings[0].split(",").length];
        for (String currentString : sourceStrings) {
            String[] splitString = currentString.split(",");
            if (splitString[ACTION_INDEX].equals("supply")) {
                results[SUPPLY_INDEX] += Integer.parseInt(splitString[QUANTITY_INDEX]);
            } else {
                results[BUY_INDEX] += Integer.parseInt(splitString[QUANTITY_INDEX]);
            }
        }
        StringBuilder resultString = new StringBuilder();
        resultString.append("supply,").append(results[SUPPLY_INDEX]).append(System.lineSeparator())
                .append("buy,").append(results[BUY_INDEX]).append(System.lineSeparator())
                .append("result,").append(results[SUPPLY_INDEX] - results[BUY_INDEX]);
        File fileTo = new File(toFileName);
        writeFile(fileTo, resultString.toString());
    }

    private String readFile(File fileName) {
        StringBuilder resultString = new StringBuilder();
        String currentString;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            currentString = reader.readLine();
            while (currentString != null) {
                resultString.append(currentString).append(" ");
                currentString = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file...", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file...", e);
        }
        return resultString.toString().strip();
    }

    private void writeFile(File fileTo, String string) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo))) {
            writer.write(string);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file...", e);
        }
    }
}
