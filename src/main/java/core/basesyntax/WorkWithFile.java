package core.basesyntax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        final String separator = System.getProperty("line.separator");
        int supplySum = 0;
        int buySum = 0;
        int result = 0;
        String data;

        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }

        try {
            data = new String(Files.readAllBytes(Paths.get(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }

        String[] dataSplit = data.split("\\r\\n");
        for (String element : dataSplit) {
            String[] elements = element.split(",");
            if (elements[0].equals("supply")) {
                supplySum += Integer.parseInt(elements[1]);
            } else if (elements[0].equals("buy")) {
                buySum += Integer.parseInt(elements[1]);
            }
            result = supplySum - buySum;
        }

        try {
            FileWriter myWriter = new FileWriter(toFileName);
            myWriter.write("supply," + supplySum + separator
                    + "buy," + buySum + separator
                    + "result," + result);
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
