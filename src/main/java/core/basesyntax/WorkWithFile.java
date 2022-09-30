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
        int[] info = getInfoFromCsvFile(fromFile);
        writeInfoToCsvFile(info[SUPPLY_INDEX],info[BUY_INDEX],toFile);
    }

    private int[] getInfoFromCsvFile(File fromFile) {
        int supply = 0;
        int buy = 0;
        try {
            List<String> lines = Files.readAllLines(fromFile.toPath());
            for (String info : lines) {
                String[] data = info.split(SEPARATED_VALUES);
                if (data[TEXT_INFO_INDEX].equals("supply")) {
                    supply += Integer.parseInt(data[1]);
                } else {
                    buy += Integer.parseInt(data[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from " + fromFile.getName(), e);
        }
        return new int[]{supply,buy};
    }

    private void writeInfoToCsvFile(int supply, int buy, File toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + (supply - buy) + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
