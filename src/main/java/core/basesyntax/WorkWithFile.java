package core.basesyntax;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try {

            CSVReader reader = new CSVReader(new FileReader(fromFileName));

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == 2) {
                    String operationType = nextLine[0];
                    int amount = Integer.parseInt(nextLine[1]);

                    if ("supply".equals(operationType)) {
                        supplyTotal += amount;
                    } else if ("buy".equals(operationType)) {
                        buyTotal += amount;
                    }
                }
            }
            reader.close();

            int result = supplyTotal - buyTotal;

            FileWriter fileWriter = new FileWriter(toFileName);

            ICSVWriter customCsvWriter = new CSVWriterBuilder(fileWriter)
                    .withLineEnd(System.lineSeparator())
                    .withSeparator(',')
                    .withQuoteChar('\0')
                    .build();

            customCsvWriter.writeNext(new String[]{"supply", String.valueOf(supplyTotal)});
            customCsvWriter.writeNext(new String[]{"buy", String.valueOf(buyTotal)});
            customCsvWriter.writeNext(new String[]{"result", String.valueOf(result)});
            customCsvWriter.close();

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


