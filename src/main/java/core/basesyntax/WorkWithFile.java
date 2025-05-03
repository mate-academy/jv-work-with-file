package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(calculateStatistics(fromFileName), toFileName);
    }

    private String calculateStatistics(String fromFileName) {
        final int AcT = 0;
        final int DigiT = 1;
        int supply = 0;
        int buy = 0;
        int result = 0;
        String [] temp = readFromFile(fromFileName);

        for (int i = 0; i < temp.length; i++) {
            String [] splitLine = temp[i].split(",");
            switch (splitLine[AcT]) {
                case "supply":
                    supply += Integer.parseInt(splitLine[DigiT]);
                    break;
                case "buy":
                    buy += Integer.parseInt(splitLine[DigiT]);
                    break;
                default:
                    break;
            }
        }
        result = supply - buy;
        return supply + " " + buy + " " + result;
    }

    private void writeDataToFile(String quantity, String destination) {
        final int SupplY = 0;
        final int BuY = 1;
        final int ResulT = 2;
        File file = new File(destination);
        String [] inputData = quantity.split(" ");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            StringBuilder builder = new StringBuilder("supply,");
            builder.append(inputData[SupplY]).append(System.lineSeparator())
                            .append("buy,").append(inputData[BuY])
                            .append(System.lineSeparator()).append("result,")
                            .append(inputData[ResulT]).append(System.lineSeparator());
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t find current file", e);
        }
    }

    private String [] readFromFile(String fileForRead) {
        File file = new File(fileForRead);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String [] contentBuffer = new String[Files.readAllLines(Path.of(fileForRead)).size()];
            for (int i = 0; i < contentBuffer.length; i++) {
                contentBuffer[i] = reader.readLine();
            }
            return contentBuffer;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
    }
}
