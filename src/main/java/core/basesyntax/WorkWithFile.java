package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String BUY_OPERATION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String [] infoFromFile = getInfo(fromFileName).split(" ");
        String countedAmount = getCount(infoFromFile);
        writeToFile(toFileName,countedAmount);
    }

    private String getInfo(String file) {
        List<String> infoFromList;
        try {
            infoFromList = Files.readAllLines(new File(file).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file " + file, e);
        }
        StringBuilder builder = new StringBuilder();
        for (String info : infoFromList) {
            builder.append(info).append(" ");
        }
        return builder.toString();
    }

    private void writeToFile(String toFileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write information into the file " + toFileName, e);
        }
    }

    private String getCount(String [] info) {
        int buy = 0;
        int supply = 0;
        int result;
        for (String rowInfo : info) {
            String[] line = rowInfo.split(",");
            if (line[0].equals(BUY_OPERATION)) {
                buy += Integer.parseInt(line[1]);
            } else {
                supply += Integer.parseInt(line[1]);
            }
        }
        result = supply - buy;
        return getAmountToString(supply, buy, result);
    }

    private String getAmountToString(int supply, int buy, int result) {
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }
}
