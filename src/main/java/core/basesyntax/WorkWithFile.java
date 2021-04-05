package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String PURPOSE_AND_VALUE_SEPARATOR = ",";
    private static final String DATA_SEPARATOR = "\n";
    private static final String CHARACTERS_AND_SIGNS_FILTER = "^a-z ^,";
    private String fromFileName;
    private String toFileName;

    public String getFromFileName() {
        return fromFileName;
    }

    public void setFromFileName(String fromFileName) {
        this.fromFileName = fromFileName;
    }

    public String getToFileName() {
        return toFileName;
    }

    public void setToFileName(String toFileName) {
        this.toFileName = toFileName;
    }

    private String[] readFileAndCreateDataArray() {
        File file = new File(getFromFileName());
        if (file.length() > 0) {
            String[] fileDataArray;
            String buffer;
            try {
                buffer = Files.readString(file.toPath())
                        .replaceAll(CHARACTERS_AND_SIGNS_FILTER, "");
                fileDataArray = buffer.split(DATA_SEPARATOR);
                return fileDataArray;
            } catch (IOException e) {
                throw new RuntimeException("Can`t read file and list data.", e);
            }
        }
        return new String[]{};
    }

    private int[] sumOfSupplyAndBuyInFile() {
        int supplyNumber = 0;
        int buyNumber = 0;
        for (String data : readFileAndCreateDataArray()) {
            String dataSubstring = data.substring(0, data.indexOf(PURPOSE_AND_VALUE_SEPARATOR));
            int valueOfData = Integer.parseInt(data.substring(data
                    .indexOf(PURPOSE_AND_VALUE_SEPARATOR) + 1).trim());
            if (dataSubstring.equals("supply")) {
                supplyNumber += valueOfData;
            } else if (dataSubstring.equals("buy")) {
                buyNumber += valueOfData;
            }
        }
        int result = supplyNumber - buyNumber;
        return new int[]{supplyNumber, buyNumber, result};
    }

    private String[] createReport() {
        int[] buyAndSupplyArray = sumOfSupplyAndBuyInFile();
        String reportBuilder = "supply," + buyAndSupplyArray[0]
                + " " + "buy," + buyAndSupplyArray[1]
                + " " + "result," + buyAndSupplyArray[2];
        return reportBuilder.split(" ");
    }

    public void getStatistic(String fromFileName, String toFileName) {
        setFromFileName(fromFileName);
        setToFileName(toFileName);
        StringBuilder b = new StringBuilder();
        b.append(createReport()[0]).append(System.lineSeparator())
                .append(createReport()[1]).append(System.lineSeparator())
                .append(createReport()[2]).append(System.lineSeparator());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.format("%10s", b));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to" + toFileName, e);
        }
    }
}

