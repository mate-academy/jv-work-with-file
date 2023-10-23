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
        try {
            String[] header = read(fromFileName);
            int[] totals = processFile(fromFileName);
            writeResult(toFileName, header, totals);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] read(String fromFileName) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(fromFileName));
        String[] header = reader.readNext();
        reader.close();
        return header;
    }

    private int[] processFile(String fromFileName) throws IOException, CsvValidationException {
        int supply = 0;
        int buy = 0;

        CSVReader reader = new CSVReader(new FileReader(fromFileName));
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine.length == 2) {
                String operationType = nextLine[0];
                int amount = Integer.parseInt(nextLine[1]);

                if ("supply".equals(operationType)) {
                    supply += amount;
                } else if ("buy".equals(operationType)) {
                    buy += amount;
                }
            }
        }
        reader.close();

        int result = supply - buy;

        return new int[]{supply, buy, result};
    }

    private void writeResult(String toFileName, String[] header, int[] totals) throws IOException {
        FileWriter fileWriter = new FileWriter(toFileName);
        ICSVWriter customCsvWriter = new CSVWriterBuilder(fileWriter)
                .withLineEnd(System.lineSeparator())
                .withSeparator(',')
                .withQuoteChar('\0')
                .build();

        customCsvWriter.writeNext(new String[]{"supply", String.valueOf(totals[0])});
        customCsvWriter.writeNext(new String[]{"buy", String.valueOf(totals[1])});
        customCsvWriter.writeNext(new String[]{"result", String.valueOf(totals[2])});
        customCsvWriter.close();
    }
}
