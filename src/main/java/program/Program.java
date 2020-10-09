package program;

import iniParser.FileFormatException;
import iniParser.INIParser;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        try {
            INIParser iniParser = new INIParser("C:\\Users\\ddale\\IdeaProjects\\INIFile\\src\\main\\resources\\test.ini");
            System.out.println(iniParser.getData());
            System.out.println(iniParser.getInteger("[TOOTTOTO]", "qwe"));
            System.out.println(iniParser.getInteger("[ACD/DC]", "dghdh"));
            System.out.println(iniParser.getDouble("[TOOTTOTO]", "qwe"));
            System.out.println(iniParser.getString("[TOOTTOTO]", "qwe"));
        } catch (NumberFormatException e) {
            System.err.println("Невозможно осуществить приведение типов");
        } catch (NullPointerException e) {
            System.err.println("Такой параметр отсутствует");
        } catch (IOException e) {
            System.out.println("Файл не найден или его расширение не .ini");
        }catch (FileFormatException e) {
            System.out.println("Неверный формат");
        }


    }
}
