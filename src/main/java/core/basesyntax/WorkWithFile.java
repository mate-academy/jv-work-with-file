package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File read = new File(fromFileName);
        File report = new File(toFileName);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(read));
            StringBuilder temporary = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                temporary.append(value).append(" ");
                value = reader.readLine();
            }
            String[] lines = temporary.toString().split(" ");
            int supply = 0;
            int buy = 0;
            for (String line : lines) {
                String[] operation = line.split(",");

                if (operation[0].equals("supply")) {
                    supply += Integer.parseInt(operation[1]);
                } else {
                    buy += Integer.parseInt(operation[1]);
                }
            }
            StringBuilder results = new StringBuilder();
            results.append("supply,").append(supply).append("\n")
                    .append("buy,").append(buy).append("\n")
                    .append("result,").append(supply - buy);

            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(report));
                bufferedWriter.write(results.toString());
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file",e);
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException("Can't close BufferedWriter");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }
}
