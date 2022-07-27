package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static void getStatistic(String fromFileName, String toFileName) {
        String supplyData = readSupplyDataFromFile(fromFileName);
        String[] resultData = countDataAfterWorkingDay(supplyData);
        File file = new File(toFileName);

        for (String data : resultData) {

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(data);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file.", e);
            }
        }
    }

    public static String readSupplyDataFromFile(String filename) {
        File file = new File(filename);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file.", e);
        }
        return stringBuilder.toString();
    }

    public static String[] countDataAfterWorkingDay(String fromFileName) {
        int countBuy = 0;
        int countSupply = 0;

        String[] supplyList = fromFileName.split("\n");
        for (String s : supplyList) {
            if (s.contains("buy")) {
                countBuy += Integer.parseInt(s.replaceAll("[\\D]", ""));
            }
            if (s.contains("supply")) {
                countSupply += Integer.parseInt(s.replaceAll("[\\D]", ""));
            }
        }
        int result = countSupply - countBuy;

        return new String[]{"supply," + countSupply + System.lineSeparator(),
                "buy," + countBuy + System.lineSeparator(),
                "result," + result};
    }
}
