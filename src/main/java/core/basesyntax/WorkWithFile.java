package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line = br.readLine();
            while (line != null) {
                String[] splitedLine = line.split(",");
                switch (splitedLine[0]) {
                    case "buy" -> buy += Integer.parseInt(splitedLine[1]);
                    case "supply" -> supply += Integer.parseInt(splitedLine[1]);
                    default -> System.out.println("unknown line: " + line);
                }
                line = br.readLine();
            }
            writeToFile(toFileName, buy, supply);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(String fileName, int buy, int supply) {
        int result = supply - buy;
        File file = new File(fileName);
        try {
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    bw.write(String.format("supply,%d%s", supply, System.lineSeparator()));
                    bw.write(String.format("buy,%d%s", buy, System.lineSeparator()));
                    bw.write(String.format("result,%d%s", result, System.lineSeparator()));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
