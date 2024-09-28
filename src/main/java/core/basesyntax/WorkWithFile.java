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

    public String calculateStatistics(String fromFileName) {
        File file = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        int result = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String splitInput = reader.readLine();

            while (splitInput != null) {
                String [] temp = new String[2];
                temp = splitInput.split(",");
                splitInput = reader.readLine();

                switch (temp[0]) {
                    case "supply":
                        supply += Integer.parseInt(temp[1]);
                        break;
                    default:
                        buy += Integer.parseInt(temp[1]);
                        break;
                }
            }
            result = supply - buy;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
        return supply + " " + buy + " " + result;
    }

    public void writeDataToFile(String quantity, String destination) {
        File file = new File(destination);
        String [] inputData = quantity.split(" ");
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
