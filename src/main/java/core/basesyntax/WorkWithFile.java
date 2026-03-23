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

        int supply = 0;
        int buy = 0;
        File file = new File(fromFileName);
        String line;
        String ls = System.lineSeparator();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] customLines = line.split(",");
                switch (customLines[0]) {
                    case "buy":
                        int numberBuy = Integer.parseInt(customLines[1]);
                        buy = buy + numberBuy;
                        break;
                    case "supply":
                        int numberSupply = Integer.parseInt(customLines[1]);
                        supply = supply + numberSupply;
                        break;
                    default:
                        System.out.println("unknown command");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t find the file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }
        String[] resultArray = new String[] {"supply," + supply + ls + "buy," + buy + ls,
                "result," + (supply - buy)};
        File finalFile = new File(toFileName);
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(finalFile));
            for (String element: resultArray) {
                writer.write(element);
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can`t close buffered writer", e);
                }
            }
        }

    }
}
