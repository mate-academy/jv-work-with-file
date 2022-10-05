package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String CARRIAGE_RETURN = "/r";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder text = new StringBuilder();
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {
            String line = bufferedReader.readLine();
            while (line != null) {
                text.append(line);
                line = bufferedReader.readLine();
                if (line != null) {
                    text.append("\n");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        String[] strings = text.toString().split("\n");
        for (String line: strings) {
            String[] strArr = line.split(",");
            switch (strArr[0]) {
                case SUPPLY: supply += Integer.parseInt(strArr[1].replaceAll(CARRIAGE_RETURN,""));
                    break;
                case BUY: buy += Integer.parseInt(strArr[1].replaceAll(CARRIAGE_RETURN,""));
                    break;
                default: ;
            }
            writeStatistics(toFileName, supply, buy);
        }
    }

    public void writeStatistics(String toFileName, int totalSupply, int totalBuy) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cant create file", e);
        }

        try (FileWriter in = new FileWriter(file)) {
            in.write("supply," + totalSupply + System.lineSeparator());
            in.write("buy," + totalBuy + System.lineSeparator());
            int result = totalSupply - totalBuy;
            in.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Cant write from file ", e);
        }
    }
}
