package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMA = ",";
    private static final String SLASH = "/";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        if (data != null) {
            int countSupply = 0;
            int countBuy = 0;
            for (String dataLine : data) {
                int indexOfComa = dataLine.indexOf(COMA) + 1;
                if (dataLine.contains(SUPPLY)) {
                    countSupply += Integer.parseInt(dataLine.substring(indexOfComa));
                }
                if (dataLine.contains(BUY)) {
                    countBuy += Integer.parseInt(dataLine.substring(indexOfComa));
                }
            }
            StringBuilder result = new StringBuilder();
            result.append("supply,").append(countSupply).append(System.lineSeparator())
                    .append("buy,").append(countBuy).append(System.lineSeparator())
                    .append("result,").append(countSupply - countBuy);
            writeToFile(result.toString(),toFileName);
        }
    }

    private void writeToFile(String data, String toFileName) {
        File file = new File(toFileName);
        if (data != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(data);
            } catch (IOException e) {
                throw new RuntimeException("Can't write to " + toFileName);
            }
        }
    }

    private String[] readFromFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(SLASH);
                line = reader.readLine();
            }
            return stringBuilder.toString().split(SLASH);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("There is no such file: " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
    }

}
