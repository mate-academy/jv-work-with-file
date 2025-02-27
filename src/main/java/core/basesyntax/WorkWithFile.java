package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        int buy = 0;
        int supply = 0;

        try (FileReader fileReader = new FileReader(fromFileName);
                 FileWriter fileWriter = new FileWriter(toFileName)) {

            BufferedReader reader = new BufferedReader(fileReader);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            String values = reader.readLine();

            while (values != null) {
                builder.append(values).append(System.lineSeparator());
                values = reader.readLine();
            }

            String[] data = builder.toString().split(System.lineSeparator());
            for (String d : data) {
                String[] buyAndSupply = d.split(",");
                if (buyAndSupply[0].equals("buy")) {
                    buy += Integer.parseInt(buyAndSupply[1]);
                } else {
                    supply += Integer.parseInt(buyAndSupply[1]);
                }
            }
            writer.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator() + "result,"
                    + (supply - buy) + System.lineSeparator());
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File can't be found " + toFileName + " " + e);
        } catch (IOException e) {
            throw new RuntimeException("File can't be read " + toFileName + " " + e);
        }
    }
}
