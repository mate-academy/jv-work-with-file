package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {

    private int sapply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        getRead(fromFileName);
        getWrite(toFileName);
    }

    public void getRead(String fromFileName) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");

                for (String word : words) {
                    if (word.startsWith("s")) {
                        sapply += Integer.parseInt((word.replaceAll("\\D", "")));
                    }
                    if (word.startsWith("b")) {
                        buy += Integer.parseInt((word.replaceAll("\\D", "")));
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
    }

    public void getWrite(String toFileName) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(toFileName))) {
            bufferedWriter.write(getReport());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file");
        }
    }

    public String getReport() {

        int result = (Math.abs(sapply - buy));
        String[] arrayReport = new String[3];
        arrayReport[0] = "supply," + sapply;
        arrayReport[1] = "buy," + buy;
        arrayReport[2] = "result," + result;
        StringBuilder report = new StringBuilder();

        for (String s : arrayReport) {
            report.append(s).append("\r\n");
        }
        return report.toString();
    }
}
