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
    private int supply = 0;
    private int buy = 0;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {
        readHealper(fromFileName);

        result = supply - buy;

        writeHelper(toFileName);
    }

    public void readHealper(String fromFileName) {
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
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }

    public void writeHelper(String toFileName) {
        File toFile = new File(toFileName);
        String finalSupply = "supply," + supply;
        String finalBuy = "buy," + buy;
        String finalResult = "result," + result;

        String[] report = new String[]{finalSupply, finalBuy, finalResult};

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
            for (String resources : report) {
                writer.write(resources);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
