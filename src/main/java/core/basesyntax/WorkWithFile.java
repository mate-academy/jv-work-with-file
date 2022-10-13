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
    private static final String RESULT = "result";
    private static final String CARRIAGE_RETURN = "\r";
    private static final String NEW_LINE = "\n";
    private static final String KOMA = ",";
    private static final String ERROR_MESSAGE_WRITE_FILE = "Cant write from file";
    private static final String ERROR_MESSAGE_CREATE_FILE = "Cant create file";
    private static final String ERROR_MESSAGE_FIND_FILE = "File not found";
    private static final String ERROR_MESSAGE_READ_FILE = "Can't read data from the file";
    private static final String EMPTY_LINE = "";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {
            String line = bufferedReader.readLine();
            while (line != null) {
                text.append(line);
                line = bufferedReader.readLine();
                if (line != null) {
                    text.append(NEW_LINE);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(ERROR_MESSAGE_FIND_FILE + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException(ERROR_MESSAGE_READ_FILE + fromFileName, e);
        }
        int[] totalSypplyAndBuy = getTotalSupplyAndBuy(text.toString());
        writeStatistics(toFileName, totalSypplyAndBuy[0], totalSypplyAndBuy[1]);

    }

    private void writeStatistics(String toFileName, int totalSupply, int totalBuy) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(ERROR_MESSAGE_CREATE_FILE, e);
        }

        try (FileWriter in = new FileWriter(file)) {
            in.write(SUPPLY + KOMA + totalSupply + System.lineSeparator());
            in.write(BUY + KOMA + totalBuy + System.lineSeparator());
            int result = totalSupply - totalBuy;
            in.write(RESULT + KOMA + result);
        } catch (IOException e) {
            throw new RuntimeException(ERROR_MESSAGE_WRITE_FILE, e);
        }
    }

    private int[] getTotalSupplyAndBuy(String text) {
        int supply = 0;
        int buy = 0;
        String[] strings = text.split(NEW_LINE);
        for (String line: strings) {
            String[] strArr = line.split(KOMA);
            switch (strArr[0]) {
                case SUPPLY: supply += Integer.parseInt(strArr[1]
                        .replaceAll(CARRIAGE_RETURN,EMPTY_LINE));
                    break;
                case BUY: buy += Integer.parseInt(strArr[1].replaceAll(CARRIAGE_RETURN,EMPTY_LINE));
                    break;
                default: ;
            }
        }
        return new int[] {supply, buy};
    }

}
