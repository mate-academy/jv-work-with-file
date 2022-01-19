package core.basesyntax;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class WorkWithFile {
    void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private void writeToFile(String toFileName, String[] arrayToWrite) {
        for (String s : arrayToWrite) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
                bufferedWriter.write(s);
                bufferedWriter.write("\n");
            } catch (IOException e) {
                throw new RuntimeException("Can't open the file " + e);
            }
        }
    }

    private String[] readFromFile(String fromFileName) {
        List<FileObject> listLines = new ArrayList<FileObject>();
        FileObject fileObject;
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            while ((line = bufferedReader.readLine()) != null) {
                String[] currentLine = line.split(",");
                stringBuilder.append(currentLine[0]).append(" ");
                fileObject = new FileObject(currentLine[0], Integer.parseInt(currentLine[1]));
                listLines.add(fileObject);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + e);
        }
        stringBuilder.setLength(0);
        Map<String, Integer> result1 = listLines.stream()
                .collect(Collectors.groupingBy(FileObject::getName,
                        Collectors.summingInt(FileObject::getSum)));
        for (Map.Entry<String, Integer> pair : result1.entrySet()) {
            stringBuilder.append(pair.getKey()).append(",").append(pair.getValue()).append(" ");
        }
        stringBuilder.append("result").append(",").append(result1.get("supply") - result1.get("buy"));
        return stringBuilder.toString().split(" ");
    }
}
