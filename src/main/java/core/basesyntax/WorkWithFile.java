package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(calculateStatistics(fromFileName), toFileName);

    }

    public int [] calculateStatistics(String fromFileName) {
        File file = new File(fromFileName);
        int [] result = new int[3];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String splitInput = reader.readLine();

            while (splitInput != null) {
                String [] temp = new String[2];
                temp = splitInput.split(",");
                splitInput = reader.readLine();

                switch (temp[0]) {
                    case "supply":
                        result[0] += Integer.parseInt(temp[1]);
                        break;
                    default:
                        result[1] += Integer.parseInt(temp[1]);
                        break;
                }
            }
            result[2] = result[0] - result[1];
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
        return result;
    }

    public void writeDataToFile(int [] inputData, String destination) {
        File file = new File(destination);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            StringBuilder builder = new StringBuilder("supply,");
            builder.append(inputData[0]).append(System.lineSeparator())
                            .append("buy,").append(inputData[1])
                            .append(System.lineSeparator()).append("result,")
                            .append(inputData[2]).append(System.lineSeparator());
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t find current file", e);
        }
    }
}
