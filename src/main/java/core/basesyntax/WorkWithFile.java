package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String str;
        String name;
        int number;
        int index;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((str = reader.readLine()) != null) {
                index = str.indexOf(",");
                name = str.substring(0, index);
                number = Integer.parseInt(str.substring(index + 1));
                if (name.equals("supply")) {
                    supply += number;
                } else if (name.equals("buy")) {
                    buy += number;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fromFileName, e);
        }

        String result = "result," + (supply - buy);
        String s = "supply," + supply;
        String b = "buy," + buy;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(s);
            writer.write(System.lineSeparator());
            writer.write(b);
            writer.write(System.lineSeparator());
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly write data to file " + toFileName, e);
        }
    }
}
