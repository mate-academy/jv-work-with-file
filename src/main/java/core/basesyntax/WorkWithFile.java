package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private int buy = 0;
    private int supply = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, getCalculation(readFromFile(fromFileName)));
    }

    private String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
                }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String getCalculation(String stringBuilder) {
        String[] stringArray = stringBuilder.split(System.lineSeparator());
        for (String string : stringArray) {
            String[] resultArray = string.split(COMMA);
            supply = resultArray[ZERO_INDEX].equals(SUPPLY)
                    ? supply + Integer.parseInt(resultArray[FIRST_INDEX]) : supply;
            buy = resultArray[ZERO_INDEX].equals(BUY)
                    ? buy + Integer.parseInt(resultArray[FIRST_INDEX]) : buy;
        }
        int result = supply - buy;
        return SUPPLY + COMMA + supply
                + System.lineSeparator()
                + BUY + COMMA + buy
                + System.lineSeparator()
                + RESULT + COMMA + (result);
    }

    private void writeToFile(String fileName, String stringBuilder) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringBuilder);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
