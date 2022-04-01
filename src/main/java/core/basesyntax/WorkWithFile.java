package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String read = bufferedReader.readLine();
            while (read != null) {
                String[] readSplit = read.split(",");
                read = bufferedReader.readLine();
                if (readSplit[0].equals("supply")) {
                    supply += Integer.parseInt(readSplit[1]);
                } else {
                    buy += Integer.parseInt(readSplit[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder stReport = new StringBuilder();
            stReport.append("supply").append(",").append(supply).append(System.lineSeparator())
                    .append("buy").append(",").append(buy).append(System.lineSeparator())
                    .append("result").append(",").append(supply - buy).append(System.lineSeparator());
            bufferedWriter.write(stReport.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
