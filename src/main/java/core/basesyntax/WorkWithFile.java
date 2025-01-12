package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            writeToFile(fromFileName, toFileName);
        } catch (IOException e) {
            System.out.println("Unknown IOException error occurred while running " + e);
        }
    }

    private int[] readFromFile(String fromFileName) throws IOException {
        File readFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        int result = 0;
        int[] finalResult = new int[3];

        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            String readLines = reader.readLine();
            while (readLines != null) {
                String[] splitter = readLines.split(",");
                switch (splitter[0]) {
                    case "supply":
                        supply += Integer.parseInt(splitter[1]);
                        break;
                    case "buy":
                        buy += Integer.parseInt(splitter[1]);
                        break;
                    default:
                        break;
                }
                readLines = reader.readLine();
            }
            result = supply - buy;
            finalResult[0] = supply;
            finalResult[1] = buy;
            finalResult[2] = result;
            return finalResult;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void writeToFile(String fromFileName, String toFileName) throws IOException {
        File writeFile = new File(toFileName);
        int[] readFromFile = readFromFile(fromFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile))) {
            writer.write("supply," + readFromFile[0]);
            writer.newLine();
            writer.write("buy," + readFromFile[1]);
            writer.newLine();
            writer.write("result," + readFromFile[2]);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
