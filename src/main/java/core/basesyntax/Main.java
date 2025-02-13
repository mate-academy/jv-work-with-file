package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();

        String inputFile = "input.csv";
        String outputFile = "output.csv";

        createInputFile(inputFile);

        workWithFile.getStatistic(inputFile, outputFile);

        System.out.println("Raport zapisany do pliku: " + outputFile);
    }

    private static void createInputFile(String fileName) {
        String content = "supply,30\n" +
                "buy,10\n" +
                "buy,13\n" +
                "supply,17\n" +
                "buy,10";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Plik wejściowy " + fileName + " został utworzony.");
        } catch (IOException e) {
            System.err.println("Błąd podczas tworzenia pliku wejściowego: " + e.getMessage());
        }
    }
}
