package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFromFile(fromFileName);
        writeToFile(content, toFileName);
    }

    private String readFromFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String s = bufferedReader.readLine();
            while (s != null) {
                builder.append(s).append(";");
                s = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + path, e);
        }
        return builder.toString();
    }

    private void writeToFile(String content, String toFileName) {
        int allSupplyForDay = 0;
        int allBuyForDay = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String[] split = content.split(";");
            for (String line : split) {
                String[] split1 = line.split(",");
                int count = Integer.parseInt(split1[1]);

                if (split1[0].equals("supply")) {
                    allSupplyForDay += count;
                }

                if (split1[0].equals("buy")) {
                    allBuyForDay += count;
                }
            }
            writer.write("supply," + allSupplyForDay + System.lineSeparator());
            writer.write("buy," + allBuyForDay + System.lineSeparator());
            writer.write("result," + (allSupplyForDay - allBuyForDay));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file " + toFileName, e);
        }
    }
}

