package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply;
    private int buy;

    public void getStatistic(String fromFileName, String toFileName) {
        String line;
        supply = 0;
        buy = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            line = reader.readLine();
            while (line != null) {
                String [] arrLine = line.split(",");
                if (arrLine[0].equals("supply")) {
                    supply += Integer.parseInt(arrLine[1]);
                }
                if (arrLine[0].equals("buy")) {
                    buy += Integer.parseInt(arrLine[1]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("File can't be reached");
        } catch (NumberFormatException e) {
            System.out.println("Wrong input data");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input data" + e);
        }
        saveReport(toFileName);
    }

    public void saveReport(String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + (supply - buy));
        } catch (IOException e) {
            System.out.println("File can't be written");
        }
    }
}
