package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int ZERO_INDEX = 0;
    static final int FIRST_INDEX = 1;
    static final int SECOND_INDEX = 2;
    private final String[] optionList = {"supply", "buy", "result"};
    private final String mySeparetor = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    public String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String inputString) {
        String[] records = inputString.split(System.lineSeparator());
        String[] result;
        int supplySum = 0;
        int buySum = 0;
        String tempStr = "";
        for (String s : records) {
            result = s.split(mySeparetor);
            if (result[ZERO_INDEX].equals(optionList[ZERO_INDEX])) {
                supplySum += Integer.parseInt(result[FIRST_INDEX]);
            } else if (result[ZERO_INDEX].equals(optionList[FIRST_INDEX])) {
                buySum += Integer.parseInt(result[FIRST_INDEX]);
            }
        }
        tempStr += optionList[ZERO_INDEX] + mySeparetor + supplySum + System.lineSeparator();
        tempStr += optionList[FIRST_INDEX] + mySeparetor + buySum + System.lineSeparator();
        tempStr += optionList[SECOND_INDEX] + mySeparetor + (supplySum - buySum)
                + System.lineSeparator();
        return tempStr;
    }

    public void writeToFile(String report, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName, false)) {
            fileWriter.append(report);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
