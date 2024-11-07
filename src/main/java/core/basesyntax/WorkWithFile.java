package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply;
    private int buy;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeToFile(toFileName);
    }

    private void readFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String string = reader.readLine();
            while (string != null) {
                String[] data = string.split(",");
                String action = data[0];
                String value = data[1];
                if (action.equals("supply")) {
                    supply += Integer.parseInt(value);
                } else {
                    buy += Integer.parseInt(value);
                }
                string = reader.readLine();
            }
            result = supply - buy;
        } catch (IOException e) {
            throw new RuntimeException("Cant read file " + fileName, e);
        }
    }

    private String getReport() {
        return "supply," + Integer.toString(supply).trim() + System.lineSeparator()
                + "buy," + Integer.toString(buy).trim() + System.lineSeparator()
                + "result," + Integer.toString(result).trim() + System.lineSeparator();
    }

    private void writeToFile(String fileName) {
        File file = new File(fileName);
        if (file.length() == 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(getReport());
            } catch (IOException e) {
                throw new RuntimeException("Cant write data to file " + fileName, e);
            }
        }
    }
}
