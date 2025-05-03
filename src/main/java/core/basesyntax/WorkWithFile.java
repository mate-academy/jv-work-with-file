package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                String value = reader.readLine();
                while (value != null) {
                    stringBuilder.append(value).append("/");
                    value = reader.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can`t read line", e);
            }
            String[] arrayString = stringBuilder.toString().split("/");
            int supply = 0;
            int buy = 0;
            for (String string : arrayString) {
                String[] str = string.split(",");
                if (str[0].equals("supply")) {
                    supply += Integer.parseInt(str[1]);
                } else if (str[0].equals("buy")) {
                    buy += Integer.parseInt(str[1]);
                }
            }
            int result = supply - buy;
            StringBuilder stringBuilderOut = new StringBuilder();
            stringBuilderOut.append("supply,").append(supply).append(System.lineSeparator());
            stringBuilderOut.append("buy,").append(buy).append(System.lineSeparator());
            stringBuilderOut.append("result,").append(result).append(System.lineSeparator());
            File toFile = new File(toFileName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
                writer.write(stringBuilderOut.toString());
            } catch (IOException e) {
                throw new RuntimeException("Can`t write date to file;", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }
}
