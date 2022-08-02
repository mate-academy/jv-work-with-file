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
        File outputFile = new File(toFileName);
        try {
            ArrayList<String> result = addLines(fromFileName);
            HashSet<String> uniqueNames = getUniqueNames(fromFileName);
            HashMap<String, Integer> names = putNames(uniqueNames);
            HashMap<String, Integer> namesStatistic = countStatistic(names, result);
            StringBuilder outputResult = createStatistic(namesStatistic);

            outputFile.createNewFile();
            Files.write(outputFile.toPath(), outputResult.toString().getBytes());

        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

    public ArrayList<String> addLines(String file) {
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
            throw new RuntimeException("File not found");
        }
    }

    public HashSet<String> getUniqueNames(String file) {
        try {
            BufferedReader fileByLine = new BufferedReader(new FileReader(file));
            String lines = fileByLine.readLine();
            HashSet<String> uniqueNames = new HashSet<>();
            String[] cutLine;
            while (lines != null) {
                cutLine = lines.split(",");
                uniqueNames.add(cutLine[0]);
                lines = fileByLine.readLine();
            }
            return uniqueNames;
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

    public HashMap<String, Integer> putNames(HashSet<String> uniqueNames) {
        HashMap<String, Integer> names = new HashMap<>();
        Iterator<String> iteration = uniqueNames.iterator();

        while (iteration.hasNext()) {
            names.put(iteration.next(),0);
        }
        return names;
    }

    public HashMap<String, Integer> countStatistic(HashMap<String, Integer> names,
             ArrayList<String> result) {
        String[] cutLine;
        int buy = 0;
        int supply = 0;

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
        return names;
    }

    public StringBuilder createStatistic(HashMap<String, Integer> namesStatistic) {
        StringBuilder outputResult = new StringBuilder();

        outputResult.append("supply").append(",").append(namesStatistic.get("supply"))
                .append(System.lineSeparator())
                .append("buy").append(",").append(namesStatistic.get("buy"))
                .append(System.lineSeparator())
                .append("result,").append(namesStatistic.get("supply") - namesStatistic.get("buy"));
        return outputResult;
    }
}
