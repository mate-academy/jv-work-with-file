package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int SUM_OPERATION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            File file = new File(fromFileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int buy = 0;
            int supply = 0;

            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                } else {
                    if (line.contains("buy")) {
                        String[] arraybuy = line.split(",");
                        buy = buy + Integer.parseInt(arraybuy[SUM_OPERATION]);
                    }
                    if (line.contains("supply")) {
                        String[] arraysupply = line.split(",");
                        supply = supply + Integer.parseInt(arraysupply[SUM_OPERATION]);
                    }
                }
            }

            StringBuilder result = new StringBuilder();
            result
                    .append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply - buy);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(result.toString());
            bufferedWriter.close();

        } catch (Exception e) {
            throw new RuntimeException("Can't correctly read data from file ", e);
        }
    }
}

