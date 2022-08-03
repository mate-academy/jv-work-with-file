package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int SUPPLY_BUY_INDEX = 0;
    static final int AMOUNT_INDEX = 1;
    static final String SPLIT_REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private String[] readFromFile(String fromFileName) {
        int supply = 0;
        int buy = 0;
        int result;
        File fromFile = new File(fromFileName);

        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();

            while (value != null) {
                builder.append(value);
                String[] splittedLine = value.split(SPLIT_REGEX);
                if (splittedLine[SUPPLY_BUY_INDEX].equals("supply")) {
                    supply += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                } else {
                    buy += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                }
                value = reader.readLine();
            }
            result = supply - buy;
            String finalSupply = "supply," + supply;
            String finalBuy = "buy," + buy;
            String finalResult = "result," + result;

            String[] report = new String[]{finalSupply, finalBuy, finalResult};
            return report;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName, String[] report) {
        File toFile = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
            for (String line : report) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
