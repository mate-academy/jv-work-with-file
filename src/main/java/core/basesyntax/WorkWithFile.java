package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int onceElemArray = 0;
    private static final int twiceElemArray = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String[] content;
        int supply = 0;
        int buy = 0;
        int result = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content = line.split(",");
                String operation = content[onceElemArray];
                int amount = Integer.parseInt(content[twiceElemArray]);

                if (operation.equals("supply")) {
                    supply += amount;
                } else if (operation.equals("buy")) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File is not found", e);
        }
        result = supply - buy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("File is not found", e);
        }
    }
}
