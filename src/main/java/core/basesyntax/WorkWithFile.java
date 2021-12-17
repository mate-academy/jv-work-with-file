package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        Path infoFile = Paths.get(fromFileName);
        try {
            List<String> list = Files.readAllLines(infoFile);
            for (String info : list) {
                String[] infoArray = info.split(",");
                switch (infoArray[0]) {
                    case ("buy") :
                        buy += Integer.valueOf(infoArray[1]);
                        break;
                    case("supply") :
                        supply += Integer.valueOf(infoArray[1]);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }
        Path reportFile = Paths.get(toFileName);
        try {
            Files.writeString(reportFile, "supply," + supply + System.lineSeparator());
            Files.writeString(reportFile,"buy," + buy + System.lineSeparator(),
                    StandardOpenOption.APPEND);
            Files.writeString(reportFile, "result," + (supply - buy), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to the file", e);
        }
    }
}
