package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final String BUY_KEYWORD = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String str = bufferedReader.readLine();
            while (str != null) {
                stringBuilder.append(str).append(COMMA_SEPARATOR);
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: ", e);
        }
        String[] stringsFromFile = stringBuilder.toString().split(COMMA_SEPARATOR);
        int supplyAllSum = 0;
        int buyAllSum = 0;
        for (int i = 0; i < stringsFromFile.length - 1; i += 2) {
            if (stringsFromFile[i].equals(BUY_KEYWORD)) {
                buyAllSum += Integer.parseInt(stringsFromFile[i + 1]);
            } else {
                supplyAllSum += Integer.parseInt(stringsFromFile[i + 1]);
            }
        }
        String[] resultStrings = new String[]{"supply,"
                + supplyAllSum, "buy," + buyAllSum, "result," + (supplyAllSum - buyAllSum)};
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String str : resultStrings) {
                bufferedWriter.write(str);
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write in the file: ", e);
        }
    }
}
