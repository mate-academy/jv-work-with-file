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

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] block = currentLine.split(",");
                String operationType = block[0].trim();
                int amount = Integer.parseInt(block[1].trim());

                if (operationType.equals("supply")) {
                    supply += amount;
                } else if (operationType.equals("buy")) {
                    buy += amount;
                }
            }

            int result = supply - buy;

            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);

        } catch (IOException e) {
            throw new RuntimeException("", e);
        }
    }
}
