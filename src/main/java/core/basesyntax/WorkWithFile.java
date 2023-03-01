package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String SPECIAL_DELIMITER = "!Delimiter@";
    public void getStatistic(String fromFileName, String toFileName) {
        File readFromFile = new File(fromFileName);
        File writeToFile = new File(toFileName);
        String[] fileArray = simplificateArray(convertFileToStringArray(readFromFile));
        if (!fileArray[0].contains("supply")) {
            String replaceString = fileArray[1];
            fileArray[1] = fileArray[0];
            fileArray[0] = replaceString;
        }
        int firstNumber = Integer.parseInt(fileArray[0].replaceAll("\\D", ""));
        int secondNumber = Integer.parseInt(fileArray[1].replaceAll("\\D", ""));
        String attachString = String.format("result,%d", firstNumber - secondNumber );
        writeStringToFile(fileArray, writeToFile, attachString);
    }
    private void writeStringToFile (String[] stringFrom, File fileTo, String result){
        try(FileWriter fileWriter = new FileWriter(fileTo)) {
        }
        catch (IOException e) {
            throw new RuntimeException("Can't clear a file content", e);
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
            for (String string:stringFrom) {
                bufferedWriter.write(string);
                bufferedWriter.write(System.lineSeparator());
            }
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write sting to file ", e);
        }
    }
    private String[] convertFileToStringArray(File file) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder string = new StringBuilder();
            String nextLine = reader.readLine();
            while (nextLine != null) {
                string.append(nextLine).append(SPECIAL_DELIMITER);
                nextLine = reader.readLine();
            }
            String[] resultArray = string.toString().split(SPECIAL_DELIMITER);
            return resultArray;
        } catch (IOException e) {
            throw new RuntimeException("Can't rewrite file content to string array ", e);
        }
    }
    private String[] simplificateArray(String[] array) {
        StringBuilder checkerString = new StringBuilder();
        for (String str:array) {
            int commaIndex = str.indexOf(',');
            String stringOption = str.substring(0, commaIndex);
            if (!(checkerString.toString().contains(stringOption))) {
                checkerString.append(stringOption).append(SPECIAL_DELIMITER);
            }
        }
        String[] reportArray = checkerString.toString().split(SPECIAL_DELIMITER);
        for (int i = 0; i < reportArray.length; ++i) {
            int sumNumber = 0;
            for (int k = 0; k < array.length; ++k) {
                if (array[k].contains(reportArray[i])) {
                    int commaIndex = array[k].indexOf(',') + 1;
                    sumNumber += Integer.parseInt(array[k].substring(commaIndex));
                }
            }

            reportArray[i] = reportArray[i].concat(String.format(",%d", sumNumber));
        }
        return reportArray;
    }
}
