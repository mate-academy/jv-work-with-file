package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        int result = 0;

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fromFileName))) { //try-with-resources

            String line;

            while ((line = reader.readLine()) != null) { // Читаємо файл, рядок за рядком

                String[] parts = line.split(",");
                String definition = parts[0];
                int amountOfDefinition = Integer.parseInt(parts[1]);
                if (definition.equals("buy")) {
                    buy += amountOfDefinition;
                }
                if (definition.equals("supply")) {
                    supply += amountOfDefinition;
                }
            }
            result = supply - buy;

            createReport(toFileName, buy, supply, result);

        } catch (IOException e) {
            e.printStackTrace(); // Виводимо стек виключення в разі помилки
        }
    }

    private void createReport(String tofileName, int buy, int supply, int result) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(tofileName))) { //try-with-resources

            writer.write("supply," + supply + System.lineSeparator()); // Записуємо текст у файл
            writer.write("buy," + buy + System.lineSeparator()); // Записуємо текст у файл
            writer.write("result," + result + System.lineSeparator()); // Записуємо текст у файл
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
