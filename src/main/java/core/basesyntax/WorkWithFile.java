package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File startFile = new File(fromFileName);
        File resultFile = new File(toFileName);
        StringBuilder startStringBuilder = new StringBuilder();
        String[] tmpArray;
        int supplySum = 0;
        int buySum = 0;

        try {
            resultFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can not create this file", e);
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(startFile));
            int value = bufferedReader.read();

            while (value != -1) {
                startStringBuilder.append((char) value);
                value = bufferedReader.read();
            }

            tmpArray = new String[startStringBuilder.length()];
            tmpArray = startStringBuilder.toString().split(",|\\n");

            for (int i = 0;i < tmpArray.length;i++) {
                if (tmpArray[i].equals("supply")) {
                    supplySum += Integer.parseInt(tmpArray[i + 1]);
                }

                if (tmpArray[i].equals("buy")) {
                    buySum += Integer.parseInt(tmpArray[i + 1]);
                }
            }

            PrintWriter writer = new PrintWriter(resultFile);
            writer.print("");
            writer.close();

            try {
                Files.write(resultFile.toPath(), "supply,".getBytes(),
                        StandardOpenOption.APPEND);
                Files.write(resultFile.toPath(), Integer.toString(supplySum).getBytes(),
                        StandardOpenOption.APPEND);
                Files.write(resultFile.toPath(), (System.lineSeparator() + "buy,").getBytes(),
                        StandardOpenOption.APPEND);
                Files.write(resultFile.toPath(), Integer.toString(buySum).getBytes(),
                        StandardOpenOption.APPEND);
                Files.write(resultFile.toPath(), (System.lineSeparator() + "result,").getBytes(),
                        StandardOpenOption.APPEND);
                Files.write(resultFile.toPath(), Integer.toString(supplySum - buySum).getBytes(),
                        StandardOpenOption.APPEND);

            } catch (IOException e) {
                throw new RuntimeException("Can not write to this file");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not work with these files", e);
        }
    }
}
