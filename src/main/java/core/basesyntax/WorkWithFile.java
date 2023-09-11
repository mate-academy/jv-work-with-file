package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int POSITION_OPERATION = 0;
    private static final int POSITION_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String captionSupply = "supply";
        String captionBuy = "buy";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] elements = line.split(",");
                if (elements[POSITION_OPERATION].equals(captionSupply)) {
                    supply += Integer.parseInt(elements[POSITION_AMOUNT]);
                } else if (elements[POSITION_OPERATION].equals(captionBuy)) {
                    buy += Integer.parseInt(elements[POSITION_AMOUNT]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(captionSupply);
            bufferedWriter.write(",");
            bufferedWriter.write(String.valueOf(supply));
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write(captionBuy);
            bufferedWriter.write(",");
            bufferedWriter.write(String.valueOf(buy));
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write("result");
            bufferedWriter.write(",");
            bufferedWriter.write(String.valueOf(supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
