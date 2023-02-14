package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String ADDITION_LINE_NAME = "supply";
    private static final String SUBTRACTION_LINE_NAME = "buy";
    private static final String RESULT_LINE_NAME = "result";
    private static final int NAME_COLUMN_ID = 0;
    private static final int VALUE_COLUMN_ID = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        Path pathFrom = Paths.get(fromFileName);
        Path pathTo = Paths.get(toFileName);
        List<Line> allLines = read(pathFrom);
        Line[] countedLines = count(allLines);
        for (Line line : countedLines) {
            stringBuilder.append(line.toString());
        }
        System.out.println(stringBuilder);
        write(stringBuilder.toString(),pathTo);
    }

    private List<Line> read(Path path) {
        List<Line> allLines = new ArrayList<>();
        try {
            List<String> allLinesString = Files.readAllLines(path);
            for (String line : allLinesString) {
                String[] lineSeparated = line.split(",");
                allLines.add(new Line(Integer.parseInt(lineSeparated[VALUE_COLUMN_ID]),
                        lineSeparated[NAME_COLUMN_ID]));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read ", e);
        }
        return allLines;
    }

    private void write(String text, Path path) {
        File file = path.toFile();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write ", e);
        }
    }

    private Line[] count(List<Line> allLines) throws RuntimeException {
        Line[] result = new Line[]{ new Line(0, ADDITION_LINE_NAME),
                new Line(0, SUBTRACTION_LINE_NAME),
                new Line(0, RESULT_LINE_NAME)};
        for (Line line : allLines) {
            if (line != null) {
                switch (line.getName()) {
                    case ADDITION_LINE_NAME:
                        result[NAME_COLUMN_ID].setValue(result[NAME_COLUMN_ID].getValue()
                                + line.getValue());
                        break;
                    case SUBTRACTION_LINE_NAME:
                        result[VALUE_COLUMN_ID].setValue(result[VALUE_COLUMN_ID].getValue()
                                + line.getValue());
                        break;
                    default:
                        throw new LineNameIsWrongException("Line names are designated incorrectly");
                }
            }
        }
        result[2].setValue(result[0].getValue() - result[1].getValue());
        return result;
    }
}
