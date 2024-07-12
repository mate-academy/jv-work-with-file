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

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals("supply")) {
                    supply += amount;
                } else if (operation.equals("buy")) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read the file with name " + fromFileName);
        }

        int result = supply - buy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Cannot write to the file with name " + toFileName);
        }
    }
}
