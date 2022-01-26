package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;

        String[] resultingData = readFromFile(fromFileName).split(("\\W+"));
        System.out.print(Arrays.toString(resultingData));

        for (int i = 0; i < resultingData.length - 1; i += 2) {
            supply += resultingData[i].equals("supply") ? Integer.parseInt(resultingData[i + 1])
                    : 0;
            buy += resultingData[i].equals("buy") ? Integer.parseInt(resultingData[i + 1])
                    : 0;
        }

        int delta = supply - buy;
        writeDataToFile(createsStatistic(supply, buy, delta), toFileName);

    }

    public static void main(String[] args) {
        WorkWithFile wf = new WorkWithFile();
        wf.getStatistic("apple.csv", "report.csv");

    }

    private String readFromFile(String filePathName) {
        File file = new File(filePathName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
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
        return builder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA)
                .append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).toString();
    }
}
