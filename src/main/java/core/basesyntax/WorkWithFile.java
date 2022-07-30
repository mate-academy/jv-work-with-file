package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        File outputFile = new File(toFileName);
        StringBuilder outputResult = new StringBuilder();
        try {
            BufferedReader fileByLine = new BufferedReader(new FileReader(file));
            String lines = fileByLine.readLine();
            ArrayList<String> result = new ArrayList<>();
            String[] cutLine;
            HashSet<String> uniqueNames = new HashSet<>();
            HashMap<String, Integer> names = new HashMap<>();
            int buy = 0;
            int supply = 0;

            while (lines != null) {
                result.add(lines);
                cutLine = lines.split(",");
                uniqueNames.add(cutLine[0]);
                lines = fileByLine.readLine();
            }

            Iterator<String> iteration = uniqueNames.iterator();

            while (iteration.hasNext()) {
                names.put(iteration.next(),0);
            }

            for (int i = 0; i < result.size();i++) {
                cutLine = result.get(i).split(",");
                if (names.containsKey(cutLine[0])) {
                    names.put(cutLine[0], names.get(cutLine[0]) + Integer.parseInt(cutLine[1]));
                }
            }

            for (String key: names.keySet()) {
                if (key.equals("supply")) {
                    supply = names.get(key);
                }
                if (key.equals("buy")) {
                    buy = names.get(key);
                }
            }
            outputResult.append("supply").append(",").append(names.get("supply"))
                    .append(System.lineSeparator())
                    .append("buy").append(",").append(names.get("buy"))
                    .append(System.lineSeparator())
                    .append("result,").append(supply - buy);

            outputFile.createNewFile();
            Files.write(outputFile.toPath(), outputResult.toString().getBytes());

        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }
}
