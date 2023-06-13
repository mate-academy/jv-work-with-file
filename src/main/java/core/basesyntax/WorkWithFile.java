package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int index = 0;
        try {
            int[] sumArray = readSumFromFile(fromFileName);
            writeInFile(sumArray[index], sumArray[index + 1], toFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int[] readSumFromFile(String fromFile) throws IOException {
        int supplySum = 0;
        int buySum = 0;
        int index = 0;
        Scanner scanner = new Scanner(new File(fromFile));
        while (scanner.hasNext()) {
            String[] strings = scanner.nextLine().split(",");
            String doing = strings[index];
            int number = Integer.parseInt(strings[index + 1]);
            if (doing.equals("supply")) {
                supplySum += number;
            } else {
                buySum += number;
            }
        }
        scanner.close();

        return new int[]{supplySum, buySum};
    }

    private void writeInFile(int sumSupply, int sumBuy, String toFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(toFile));
        String stringBuilder = "supply," + sumSupply + System.lineSeparator()
                + "buy," + sumBuy + System.lineSeparator()
                + "result," + (sumSupply - sumBuy);
        writer.write(stringBuilder);
        writer.close();
    }
}

