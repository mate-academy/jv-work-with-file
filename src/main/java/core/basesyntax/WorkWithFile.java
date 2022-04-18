package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int[] buySupplyTotal = new int[2];
        File file = new File(fromFileName);
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fromFileName + " not found");
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        while (true) {
            try {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] string = line.split(",");
                if (Objects.equals(string[0], "supply")) {
                    buySupplyTotal[0] += Integer.parseInt(string[1]);
                } else if (string[0].equals("buy")) {
                    buySupplyTotal[1] += Integer.parseInt(string[1]);
                }
            } catch (IOException e) {
                throw new RuntimeException("IOException in bufferedReader while loop");
            }
        }
        String result = "supply," + buySupplyTotal[0] + "\n"
                        + "buy," + buySupplyTotal[1] + "\n"
                        + "result," + (buySupplyTotal[0] - buySupplyTotal[1]);
        file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("IOException in bufferedWriter when writing to file " + toFileName, e);
        }
    }
}
