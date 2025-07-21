package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String APPLE_RESULT_FILE = "appleResult.csv";

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", APPLE_RESULT_FILE);
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {

            String newLine = bufferedReader.readLine();
            if (!newLine.matches("^([a-zA-Z_]+),(\\d+)$")) {
                newLine = bufferedReader.readLine();
            }

            while (newLine != null) {
                String[] data = newLine.split(",");
                if (data[0].equals("buy")) {
                    buy += Integer.parseInt(data[1]);
                } else {
                    supply += Integer.parseInt(data[1]);
                }
                newLine = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open the file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }

        String supplyStr = "supply," + supply + "\n";
        String buyStr = "buy," + buy + "\n";
        String resultStr = "result," + (supply - buy);

        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(supplyStr);
            fileWriter.write(buyStr);
            fileWriter.write(resultStr);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }

    }

}
