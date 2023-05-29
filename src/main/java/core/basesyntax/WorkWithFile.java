package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    private List<String> readFile(String pathToFile) throws IOException {
        return Files.readAllLines(Path.of(pathToFile));
    }

    private String[] convertToReport(List<String> lines) {
        String[] reportArray = new String[3];
        int totalBuy = 0;
        int totalSupply = 0;
        for (String line : lines) {
            String[] str = line.split(",");
            if (str[0].equals(SUPPLY)) {
                totalSupply = totalSupply + Integer.valueOf(str[1]);
            }
            if (str[0].equals(BUY)) {
                totalBuy = totalBuy + Integer.valueOf(str[1]);
            }
        }
        reportArray[0] = SUPPLY + "," + totalSupply;
        reportArray[1] = BUY + "," + totalBuy;
        reportArray[2] = RESULT + "," + (totalSupply - totalBuy);
        return reportArray;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            List<String> lines = readFile(fromFileName);
            String[] reportArray = convertToReport(lines);
            writeReport(reportArray, toFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeReport(String[] reportArray, String toFileName) throws IOException {
        FileWriter fileWriter = new FileWriter(toFileName);
        BufferedWriter bf = new BufferedWriter(fileWriter);
        for (String line : reportArray) {
            bf.write(line);
            bf.newLine();
        }
        bf.close();
    }

}
