package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                if (split[0].equals("supply")) {
                    supply += Integer.parseInt(split[1]);
                } else {
                    buy += Integer.parseInt(split[1]);
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t open file",e);
        } catch (IOException e) {
            throw new RuntimeException("Runtime Exception", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator() + "buy," + buy
                    + System.lineSeparator() + "result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Runtime Exception", e);
        }
    }
}
