package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName);
    }
    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private int buy;
    private int supply;
    private void readFromFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            buy = 0;
            supply = 0;
            while (line != null) {
                String[] value = line.split(",");
                switch (value[ZERO_INDEX]) {
                    case BUY:
                        buy = buy + Integer.parseInt(value[FIRST_INDEX]);
                        break;
                    case SUPPLY:
                        supply = supply + Integer.parseInt(value[FIRST_INDEX]);
                        break;
                    default:
                        System.err.println("Corrupted data in input file");
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fileName, e);
        }
    }

    private void writeToFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file" + fileName, e);
        }
    }
}
