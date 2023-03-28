package core.basesyntax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private int supplySum = 0;
    private int buySum = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        int result = 0;
        String data = readFromFile(fromFileName);
        if (data != null) {
            result = createReport(data);
        }
        if (createNewFile(toFileName)) {
            writeToFile(toFileName, result);
        }
    }

    private boolean createNewFile(String toFileName) {
        boolean fileCreated;
        File file = new File(toFileName);
        try {
            fileCreated = file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
        return fileCreated;
    }

    private String readFromFile(String fromFileName) {
        String data;
        try {
            data = new String(Files.readAllBytes(Paths.get(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return data;
    }

    private int createReport(String data) {
        String[] dataSplit = data.split("\\r\\n");
        for (String element : dataSplit) {
            String[] elements = element.split(",");
            if (elements[0].equals("supply")) {
                supplySum += Integer.parseInt(elements[1]);
            } else if (elements[0].equals("buy")) {
                buySum += Integer.parseInt(elements[1]);
            }
        }
        return supplySum - buySum;
    }

    public void writeToFile(String toFileName, int result) {
        final String separator = System.lineSeparator();
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

