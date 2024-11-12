package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int buy = 0;
            int supply = 0;
            String line;
            String[] data;
            while ((line = reader.readLine()) != null) {
                data = line.split(",");
                switch (data[0]) {
                    case "buy":
                        buy += Integer.parseInt(data[1]);
                        break;
                    case "supply":
                        supply += Integer.parseInt(data[1]);
                        break;
                    default:
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                String sep = System.lineSeparator();
                String form = "supply,%d" + sep + "buy,%d" + sep + "result,%d";
                writer.write(String.format(form, supply, buy, supply - buy));
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
