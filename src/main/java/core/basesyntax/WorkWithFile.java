package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    private static final String FIRST_REPORT_WORD_VALUE = "supply";
    private static final String SECOND_REPORT_WORD_VALUE = "buy";
    private static final String THIRD_REPORT_WORD_VALUE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeReport(createReport(readData(fromFileName)), toFileName);

    }

    private String[] createReport(ArrayList<String> inputData) {
        int buySum = 0;
        int suppliesSum = 0;

        for (String element : inputData) {
            int value = Integer.parseInt(element.substring(element.indexOf(",") + 1));
            if (element.contains(FIRST_REPORT_WORD_VALUE)) {
                suppliesSum += value;
            }
            if (element.contains(SECOND_REPORT_WORD_VALUE)) {
                buySum += value;
            }
        }
        return new String[]{String.format(" %s,%s%s", FIRST_REPORT_WORD_VALUE, suppliesSum,
                System.lineSeparator()),
                String.format("%s,%s%s", SECOND_REPORT_WORD_VALUE, buySum, System.lineSeparator()),
                String.format("%s,%s", THIRD_REPORT_WORD_VALUE, suppliesSum - buySum)};

    }

    private ArrayList<String> readData(String fromFileName) {
        ArrayList<String> inputData = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fromFileName))) {
            String readData;
            while ((readData = fileReader.readLine()) != null) {
                inputData.add(readData);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file.", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file.", e);
        }
        return inputData;
    }

    private void writeReport(String[] data, String toFileName) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            for (String element : data) {
                fileWriter.write(element);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write in the file.", e);
        }
    }
}
