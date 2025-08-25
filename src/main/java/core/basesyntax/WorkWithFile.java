package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public static void getStatistic(String fromFileName, String toFileName) {

        int supply = 0;
        int buy = 0;
        int result;
        String line;
        String[] lineSplit;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                lineSplit = line.split(",");

                int num;

                if (lineSplit[0].equals("supply")) {
                    num = Integer.parseInt(lineSplit[1]);
                    supply += num;
                }

                else if (lineSplit[0].equals("buy")) {
                    num = Integer.parseInt(lineSplit[1]);
                    buy += num;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read from a file.",e);
        }

        try (BufferedWriter write = new BufferedWriter(new FileWriter(toFileName))) {
            result = supply - buy;

            write.write("supply," + supply + System.lineSeparator() +
                    "buy," + buy + System.lineSeparator() + "result," + result);
        }

        catch (IOException e) {
            throw new RuntimeException("Can't write to a file.", e);
        }
    }
}
