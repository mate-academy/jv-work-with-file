package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                if (line.substring(0, line.indexOf(",")).equals("supply")) {
                    supply += Integer.parseInt(line.substring(line.indexOf(",") + 1));
                } else if (line.substring(0, line.indexOf(",")).equals("buy")) {
                    buy += Integer.parseInt(line.substring((line.indexOf(",") + 1)));
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException("The file cannot be read" , ioe);
        }
        builder.append("supply" + "," + supply + System.lineSeparator()).append("buy" + "," + buy + System.lineSeparator())
                .append("result" + "," + (supply - buy));

        try (BufferedWriter writeInFile = new BufferedWriter(new FileWriter(toFileName, true))){
            writeInFile.write(builder.toString());
        } catch (IOException ioe) {
            throw new RuntimeException("Can't write to file!", ioe);
        }
    }
}