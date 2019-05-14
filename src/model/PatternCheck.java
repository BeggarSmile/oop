package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCheck {
    /*todo 1) Нафига ты его функционал через инстанс сделал? Да еще в конструктор запихнул логику проверки. Тебе экземпляр класса нафиг не нужен
     лучше сделай статичный метод boolean check(String registrationNumber) и в нем выполняй проверку и возвращай сразу результат
     поля matcher и check нафиг не нужны. Оставляешь один статичный атрибут pattern
    */
    //todo 2) паттерн не коррекен : а) не все буквы участвуют в номере б) используй квантификаторы и эскейпы, заменяющие цифры
    private static Pattern pattern = Pattern.compile("[ABEKMHOPCTYX][\\d]{3}[ABEKMHOPCTYX]{2}[\\d]{2,3}");

    public static boolean check(String registrationNumber) {
        Matcher matcher = pattern.matcher(registrationNumber);
        return matcher.find();
    }
}
