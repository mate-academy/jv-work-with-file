package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(fromFileName))) {
            while (scanner.hasNext()) {
                String[] strings = scanner.nextLine().split(",");
                int number = Integer.parseInt(strings[SECOND_INDEX]);
                String doing = strings[FIRST_INDEX];
                if (doing.equals("supply")) {
                    sumSupply += number;
                } else {
                    sumBuy += number;
                }
            }
            stringBuilder.append("supply,").append(sumSupply).append(System.lineSeparator());
            stringBuilder.append("buy,").append(sumBuy).append(System.lineSeparator());
            stringBuilder.append("result,").append(sumSupply - sumBuy);
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
