package core.basesyntax;

import java.io.*;
import java.util.Arrays;


public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        int rows = countRows(fromFileName);

        String stringLine;
        StringBuilder stringBuilder = new StringBuilder();
        String[] params = new String[rows];
        int[] values = new int[rows];

        try (BufferedReader br = new BufferedReader(new FileReader(fromFile))) {
            for (int i = 0; i < rows; i++) {
                stringLine = br.readLine();
                params[i] = stringLine.substring(0, stringLine.indexOf(","));
                values[i] = Integer.valueOf(stringLine.substring(stringLine.indexOf(",")+1));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }

        System.out.println(Arrays.toString(getUnique(params)));

        String[] uniqueParams = getUnique(params);
        String[] resultArray = new String[uniqueParams.length + 1];
        int num = 0;
        int sum = 0;
        for (int i = 0; i <= uniqueParams.length; i++) {
            resultArray[i] = uniqueParams[i];
            for (int j = 0; j < params.length; j++) {
                if (params[j].equals(uniqueParams[i])) {
                    sum += values[j];
                }
            }
            resultArray[i] +=
        }


        try {
            writeToFile(toFileName, params, values);
        } catch (WorkWithFilesException e) {
            throw new RuntimeException("Can't run writeToFile method", e);
        }

        String[] resultString = stringBuilder.toString().split(" ");
        Arrays.sort(resultString);
        System.out.println(Arrays.toString(resultString));

    }

    public void writeToFile(String toFileName, String[] params, int[] values) throws WorkWithFilesException {
        if (params.length != values.length) {
            throw new WorkWithFilesException("Params and values has different length: params[" + params.length
                    + "] and values[" + values.length + "].");
        }

        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < params.length; i++) {
            stringBuilder.append(params[i]).append(",").append(values[i]).append("\n");
        }

        File file = new File(toFileName);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try{
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int countRows(String fileName) {
        int rows = 0;
        File file = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.readLine() != null) {
                rows++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + file.getAbsoluteFile(), e);
        }
        return rows;
    }

    public String[] getUnique(String[] array) {
        StringBuilder builder = new StringBuilder();
        for (String param :
                array) {
            if (builder.indexOf(param) < 0) {
                builder.append(param).append(";");
            }
        }
        return builder.toString().split(";");
    }
}
