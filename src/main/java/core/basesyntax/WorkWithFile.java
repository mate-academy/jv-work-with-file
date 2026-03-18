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
    private static int supply = 0;
    private static int buy = 0;
    private static int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String[] content;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            supply = 0;
            buy = 0;
            result = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content = line.split(",");
                String operation = content[onceElemArray].trim();
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
        writer(toFileName);

    }

    public void writer(String toFileName) {
        result = supply - buy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }
}
