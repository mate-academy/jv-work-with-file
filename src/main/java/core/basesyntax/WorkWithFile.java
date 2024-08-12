package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    public static final int ACTION_PART_INDEX = 0;
    public static final int NUMBERS_PART_INDEX = 1;
    public static final int SUPPLY_PART_INDEX = 0;
    public static final int BUY_PART_INDEX = 1;
    public static final String SUPPLY_STRING = "supply";
    public static final String SEPARATION_PLACE = ",";
    public static final String BUY_STRING = "buy";
    public static final String REPORT_STRING = "result";

    private ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> linesList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                linesList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return linesList;
    }

    private int[] generateReport(ArrayList<String> readFileStrings) {
        int supplySum = 0;
        int buySum = 0;
        for (String string : readFileStrings) {
            String[] splitedString = string.split(SEPARATION_PLACE);
            if (splitedString[ACTION_PART_INDEX].equals(SUPPLY_STRING)) {
                supplySum += Integer.parseInt(splitedString[NUMBERS_PART_INDEX]);
            } else {
                buySum += Integer.parseInt(splitedString[NUMBERS_PART_INDEX]);
            }
        }
        return new int[]{supplySum, buySum};
    }

    private void writeIntoFile(int[] sums, String fileName) {
        StringBuilder report = new StringBuilder();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            report = new StringBuilder(SUPPLY_STRING + "," + sums[SUPPLY_PART_INDEX]
                    + System.lineSeparator()
                    + BUY_STRING + "," + sums[BUY_PART_INDEX] + System.lineSeparator()
                    + REPORT_STRING + "," + (sums[SUPPLY_PART_INDEX] - sums[BUY_PART_INDEX]));
            bufferedWriter.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        writeIntoFile(generateReport(readFromFile(fromFileName)),toFileName);
    }
}

