package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int TEXT_INFO_INDEX = 0;
    private static final String SEPARATED_VALUES = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        List<String> listFormat = getListFromCsvFile(fromFile);
        int[] info = getSupplyAndBuy(listFormat);
        writeInfoToCsvFile(info[SUPPLY_INDEX],info[BUY_INDEX],toFile);
    }

    private List<String> getListFromCsvFile(File fromFile) {
        List<String> lines;
        try {
            lines = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from " + fromFile.getName(), e);
        }
        return lines;
    }

    private int[] getSupplyAndBuy(List<String> lines) {
        int supply = 0;
        int buy = 0;
        for (String info : lines) {
            String[] data = info.split(SEPARATED_VALUES);
            if (data[TEXT_INFO_INDEX].equals("supply")) {
                supply += Integer.parseInt(data[1]);
            } else {
                buy += Integer.parseInt(data[1]);
            }
        }
        return new int[]{supply, buy};
    }

    private void writeInfoToCsvFile(int supply, int buy, File toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                         .append("buy,").append(buy).append(System.lineSeparator())
                         .append("result,").append(supply - buy).append(System.lineSeparator());
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't find file " + toFile.getName(), e);
        }
    }
}
