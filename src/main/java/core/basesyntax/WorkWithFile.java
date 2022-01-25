package core.basesyntax;

import java.io.*;
import java.util.Arrays;

public class WorkWithFile {

    private final String SUPPLY = "supply";
    private final String BUY = "buy";
    private final String RESULT = "result";
    private final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        int result = 0;

        StringBuilder build = new StringBuilder(readFromFile(fromFileName));
        String[] resultingData = build.toString().split("\\W+");
        for (int i = 0; i < resultingData.length - 1; i += 2) {
            supply += resultingData[i].equals("supply") ? Integer.parseInt(resultingData[i + 1]) : 0;
            buy += resultingData[i].equals("buy") ? Integer.parseInt(resultingData[i + 1]) : 0;
        }
        result = buy - supply;
        writeDataToFile(createsStatistic(supply, buy, result), toFileName);


    }

    private String readFromFile(String filePathName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePathName));
            String readFromFile;
            while ((readFromFile = reader.readLine()) != null) {
                builder.append(readFromFile).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Sorry, file was not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Sorry, can not read from file", e);
        }
        return builder.toString().trim();
    }

    private void writeDataToFile(String inputData, String filePathName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePathName));
            writer.write(inputData);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("can't write data to file");
        }
    }

    private String createsStatistic(int supply, int buy, int result) {
        StringBuilder builder = new StringBuilder();
        return builder.append(SUPPLY)
                .append(COMMA).append(supply)
                .append(System.lineSeparator()).append(BUY)
                .append(COMMA).append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(result).toString();
    }
}

