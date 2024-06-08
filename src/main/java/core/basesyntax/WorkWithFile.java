package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fromFileName)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            int[] data = getDataFromFile(reader);
            supply = data[0];
            buy = data[1];
            result = data[2];
            writeDataToTheFile(writer, supply, buy, result);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong.");
        }
    }

    public int[] getDataFromFile(BufferedReader reader) throws IOException {
        int[] array = new int[3];
        int supply = 0;
        int buy = 0;
        int result = 0;
        String line = reader.readLine();
        while (line != null) {
            String[] separatedLine = line.split(",");
            if (separatedLine[0].equals(SUPPLY)) {
                supply += Integer.parseInt(separatedLine[1]);
            } else if (separatedLine[0].equals(BUY)) {
                buy += Integer.parseInt(separatedLine[1]);
            }
            line = reader.readLine();
        }
        result = supply - buy;
        array[0] = supply;
        array[1] = buy;
        array[2] = result;
        return array;
    }

    public void writeDataToTheFile(BufferedWriter writer, int supply, int buy,
                                   int result) throws IOException {
        writer.write("supply," + supply + System.lineSeparator());
        writer.write("buy," + buy + System.lineSeparator());
        writer.write("result," + result);
    }
}
