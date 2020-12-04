package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    int supply = 0;
    int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            writeToFile(toFileName, readFromFile(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    public void writeToFile(String fileName, String data) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        bufferedWriter.write(data);
        bufferedWriter.close();
    }

    public String readFromFile(String fileName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String value = bufferedReader.readLine();
        while (value != null) {
            String[] split = value.split(",");
            if (split[0].equals("supply")) {
                supply += Integer.parseInt(split[1]);
            } else {
                buy += Integer.parseInt(split[1]);
            }
            value = bufferedReader.readLine();
        }
        return stringBuilder.append("supply,").append(supply).append("\n").append("buy,")
                .append(buy).append("\n").append("result,").append(supply - buy).toString();
    }
}
