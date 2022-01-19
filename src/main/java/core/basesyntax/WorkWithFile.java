package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class WorkWithFile {
    void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private void writeToFile(String toFileName, String arrayToWrite) {
            try (BufferedWriter bufferedWriter
                         = new BufferedWriter(new FileWriter(toFileName, true))) {
                bufferedWriter.write(arrayToWrite);
            } catch (IOException e) {
                throw new RuntimeException("Can't open the file " + e);
            }
    }

    private String readFromFile(String fromFileName) {
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
        Map<String, Integer> mapResult = listLines.stream()
                .collect(Collectors.groupingBy(FileObject::getName,
                        Collectors.summingInt(FileObject::getSum)));
        stringBuilder.append("supply,").append(mapResult.get("supply")).append(System.lineSeparator());
        stringBuilder.append("buy,").append(mapResult.get("buy")).append(System.lineSeparator());
        stringBuilder.append("result")
                .append(",").append(mapResult.get("supply") - mapResult.get("buy")).append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
