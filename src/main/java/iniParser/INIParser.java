package iniParser;

import java.io.*;
import java.util.HashMap;

public class INIParser {
    private String fileName;
    private HashMap<String, HashMap<String, String>> data;

    public INIParser(String fileName) throws IOException, FileFormatException {
        File file = new File(fileName);
        if (!file.getName().endsWith("ini"))
            throw new FileFormatException();
        this.fileName = fileName;
        loadData();
    }

    public HashMap<String, HashMap<String, String>> getData() {
        return data;
    }

    public void loadData() throws IOException {
        data = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))) {
            String str = bufferedReader.readLine();
            while (bufferedReader.ready()) {
                if (str.contains(";")) {
                    str = str.split(";")[0].strip();
                }
                if (str.matches("\\[.*]")) {
                    String key = str;
                    while (key.matches("\\[.*].*?") && bufferedReader.ready()) {
                        str = bufferedReader.readLine();
                        if (str.matches("\\[.*].*?"))
                            break;
                        if (str.strip().startsWith(";") || str.equals("")) {
                            continue;
                        }
                        if (str.contains(";")) {
                            str = str.split(";")[0];
                        }
                        String[] valueEntry = str.split("=");
                        if (!this.data.containsKey(key)) {
                            this.data.put(key.strip(), new HashMap<>());
                        }
                        this.data.get(key).put(valueEntry[0].strip(), valueEntry[1].strip());
                    }
                } else
                    str = bufferedReader.readLine();
            }
        }
    }

    public int getInteger(String block, String parameter) {
        int value;
        try {
            value = Integer.parseInt(this.data.get(block).get(parameter));
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return value;
    }

    public double getDouble(String block, String parameter) {
        double value;
        try {
            value = Double.parseDouble(this.data.get(block).get(parameter));
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return value;
    }

    public String getString(String block, String parameter) {
        String value = "";
        try {
            value = this.data.get(block).get(parameter);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return value;
    }

}
