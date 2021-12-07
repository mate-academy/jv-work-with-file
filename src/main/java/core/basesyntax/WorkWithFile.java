package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder outStringBuilder = new StringBuilder();
        String sourseLine;
        String[] sourseLineArr;
        int supply = 0;
        int buy = 0;
        File inputFile = new File(fromFileName);
        File outFile = new File(toFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            sourseLine = reader.readLine();
            while (sourseLine != null) {
                sourseLineArr = sourseLine.split(",");
                if (sourseLineArr[0].equals("supply")) {
                    supply += Integer.parseInt(sourseLineArr[1]);
                } else if (sourseLineArr[0].equals("buy")) {

                    buy += Integer.parseInt(sourseLineArr[1]);
                }
                sourseLine = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(" Can`t open file");
            return;
        } catch (IOException e) {
            System.out.println(" Can`t read file");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(outStringBuilder.append("supply,")
                    .append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy)
                    .append(System.lineSeparator())
                    .append("result,").append(supply - buy)
                    .append(System.lineSeparator()).toString());
        } catch (IOException e) {
            System.out.println(" Can`t create file");
        }
    }
}
