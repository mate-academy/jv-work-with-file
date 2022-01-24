package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = ",";
    private static final String SUPPLYWORDS = "supply";
    private static final String BUYWORDS = "buy";
    private static final String RESULTWORDS = "result";
    private int supply = 0;
    private int buy = 0;
    private int result = 0;
    private String value = "";

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        createReport(toFileName);
    }

    public void readFile(String fromFileName) {
        File fileIn = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileIn))) {
            while ((value = reader.readLine()) != null) {
                String[] numbersString = value.split(REGEX);
                if (numbersString[0].equals(SUPPLYWORDS)) {
                    supply = supply + Integer.parseInt(numbersString[1]);
                }
                if (numbersString[0].equals(BUYWORDS)) {
                    buy = buy + Integer.parseInt(numbersString[1]);
                }
            }
            result = supply - buy;
        } catch (IOException e) {
            System.out.println("Can`t read file with name" + fileIn);
        }
    }

    public void createReport(String toFileName) {
        File fileOut = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut, true))) {
            writer.write(SUPPLYWORDS + REGEX + supply + System.lineSeparator());
            writer.write(BUYWORDS + REGEX + buy + System.lineSeparator());
            writer.write(RESULTWORDS + REGEX + result + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Can`t write data to file" + fileOut);
        }
    }
}


