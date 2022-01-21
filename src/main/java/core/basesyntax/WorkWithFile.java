package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        if (data != null) {
            int countSupply = 0;
            int countBuy = 0;
            for (String dataLine : data) {
                int indexOfComa = dataLine.indexOf(',') + 1;
                if (dataLine.contains("supply")) {
                    countSupply += Integer.parseInt(dataLine.substring(indexOfComa));
                }
                if (dataLine.contains("buy")) {
                    countBuy += Integer.parseInt(dataLine.substring(indexOfComa));
                }
            }
            String result = "supply,"
                    + countSupply
                    + '/'
                    + "buy,"
                    + countBuy
                    + '/'
                    + "result,"
                    + (countSupply - countBuy);
            String[] dataToReport = result.split("/");
            writeToFile(dataToReport,toFileName);
        }
    }

    private void writeToFile(String[] data, String toFileName) {
        File file = new File(toFileName);
        if (data != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                for (String dataLine : data) {
                    writer.write(dataLine);
                    writer.newLine();
                }
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
            if (line != null) {
                while (line != null) {
                    stringBuilder.append(line).append("/");
                    line = reader.readLine();
                }
                return stringBuilder.toString().split("/");
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("There is no such file: " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
    }

}
