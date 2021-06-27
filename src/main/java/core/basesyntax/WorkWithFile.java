package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_REGEX = ",";
    private static final String MATCHING_VALUE = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String analysedData = analyseDataFromFile(fromFileName);
        writeResultToFile(analysedData, toFileName);
    }

    private String analyseDataFromFile(String fromFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] separatedValues = line.split(SPLIT_REGEX);
                if (separatedValues[0].equals(MATCHING_VALUE)) {
                    supply += Integer.parseInt(separatedValues[1]);
                } else {
                    buy += Integer.parseInt(separatedValues[1]);
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File is missing" + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read file" + fromFileName, e);
        }
        return ("supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator() + "result," + (supply - buy));
    }

    private void writeResultToFile(String analysedResult, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(analysedResult);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write data to file" + toFileName, e);
        }
    }
}

