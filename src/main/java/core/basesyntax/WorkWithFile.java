package core.basesyntax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) throws FileNotFoundException {
        File file = new File(fromFileName);
        String stringSupply = "supply";
        String stringBuy = "buy";
        String[] arrayString;
        int supply = 0;
        int buy = 0;
        int result = 0;
        String myResult;

        try {
            List<String> stringList = Files.readAllLines(file.toPath());
            for (String string : stringList) {
                arrayString = string.split(",");
                if (arrayString[0].equals(stringSupply)) {
                    supply += Integer.valueOf(arrayString[1]);
                }
                if (arrayString[0].equals(stringBuy)) {
                    buy += Integer.valueOf(arrayString[1]);
                }
            }
            result = supply - buy;
        } catch (IOException e) {
            throw new FileNotFoundException("Can't read data from file");
        }

        myResult = stringSupply + ',' + supply + System.lineSeparator()
                + stringBuy + ',' + buy + System.lineSeparator()
                + "result," + result;

        file = new File(toFileName);
        try {
            Files.write(file.toPath(), myResult.getBytes());
        } catch (IOException e) {
            throw new FileNotFoundException("Can't read data from file");
        }

        return;
    }
}
