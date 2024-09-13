package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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
                String[] element = line.split(",");
                String firstElement = element[0];
                int secondElement = Integer.parseInt(element[1]);

                if (firstElement.equals("supply")) {
                    supply += secondElement;
                } else if (firstElement.equals("buy")) {
                    buy += secondElement;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found:" + e);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the file: " + e);
        }

        int result = supply - buy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to the file: " + e);
        }
    }
}
