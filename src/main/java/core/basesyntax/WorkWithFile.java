package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File outputFile = new File(toFileName);
        try {
            ArrayList<String> result = readFromFile(fromFileName);
            int[] finalResult = countStatistic(result);
            StringBuilder outputResult = createStatistic(finalResult);
            outputFile.createNewFile();
            Files.write(outputFile.toPath(), outputResult.toString().getBytes());

        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

    public ArrayList<String> readFromFile(String file) {
        try {
            BufferedReader fileByLine = new BufferedReader(new FileReader(file));
            String lines = fileByLine.readLine();
            ArrayList<String> result = new ArrayList<>();
            while (lines != null) {
                result.add(lines);
                lines = fileByLine.readLine();
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }

    public int[] countStatistic(ArrayList<String> result) {
        int[] buySupply = new int[2];
        String[] cutLine;

        for (int i = 0; i < result.size();i++) {
            cutLine = result.get(i).split(",");
            if (cutLine[0].equals("buy")) {
                buySupply[0] = buySupply[0] + Integer.parseInt(cutLine[1]);
            } else {
                buySupply[1] = buySupply[1] + Integer.parseInt(cutLine[1]);
            }
        }
        return buySupply;
    }

    public StringBuilder createStatistic(int[] buySupply) {
        StringBuilder outputResult = new StringBuilder();

        outputResult.append("supply").append(",").append(buySupply[1])
                .append(System.lineSeparator())
                .append("buy").append(",").append(buySupply[0])
                .append(System.lineSeparator())
                .append("result,").append(buySupply[1] - buySupply[0]);
        return outputResult;
    }
}
