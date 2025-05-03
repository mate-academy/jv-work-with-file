package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Path infoFile = Paths.get(fromFileName);
        try {
            List<String> list = Files.readAllLines(infoFile);
            String report = createReport(list);
            writeToFile(report, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file" + infoFile, e);
        }
    }

    public String createReport(List<String> list) {
        int buy = 0;
        int supply = 0;
        for (String info : list) {
            String[] infoArray = info.split(",");
            if (infoArray[0].equals("buy")) {
                buy += Integer.parseInt(infoArray[1]);
            } else if (infoArray[0].equals("supply")) {
                supply += Integer.parseInt(infoArray[1]);
            }
        }
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    public void writeToFile(String report,String toFileName) {
        Path reportFile = Paths.get(toFileName);
        try {
            Files.writeString(reportFile, report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to the file" + reportFile, e);
        }
    }
}
