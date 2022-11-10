
package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] supplyBuy = calculateStatistic(fromFileName);
        writeFile(toFileName, createReport(supplyBuy[0], supplyBuy[1]));
    }

    private int[] calculateStatistic(String fromFileName) {
        int supply = 0;
        int buy = 0;
        String[] stringFile = readFile(fromFileName);
        for (int i = 0; i < stringFile.length; i++) {
            String[] splitData = stringFile[i].split(",");
            if (splitData[0].equals("supply")) {
                supply += convertStringDataToInteger(splitData[1]);
            }
            if (splitData[0].equals("buy")) {
                buy += convertStringDataToInteger(splitData[1]);
            }
        }
        return new int[]{supply, buy};
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }

    private String[] readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        String[] split = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
            split = stringBuilder.toString().split("\n");
        } catch (IOException e) {
            throw new RuntimeException("File can't read", e);
        }
        return split;
    }

    private void writeFile(String nameFile, String result) {
        File file = new File(nameFile);
        try {
            file.createNewFile();
            Files.write(file.toPath(), result.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("File can't creating", e);
        }
    }

    private int convertStringDataToInteger(String stringInteger) {
        String data = stringInteger.substring(0, stringInteger.length() - 1);
        return Integer.parseInt(data);
    }
}
