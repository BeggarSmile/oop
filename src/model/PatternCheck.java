package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCheck {
    /*todo 1) Нафига ты его функционал через инстанс сделал? Да еще в конструктор запихнул логику проверки. Тебе экземпляр класса нафиг не нужен
     лучше сделай статичный метод boolean check(String registrationNumber) и в нем выполняй проверку и возвращай сразу результат
     поля matcher и check нафиг не нужны. Оставляешь один статичный атрибут pattern
    */
    //todo 2) паттерн не коррекен : а) не все буквы участвуют в номере б) используй квантификаторы и эскейпы, заменяющие цифры
    private Pattern pattern = Pattern.compile("[ABC][0-9][0-9][0-9][A-Z][A-Z][0-9][0-9]");
    private Matcher matcher;
    private boolean check;

    public PatternCheck(String registrationNumber) {
        this.matcher = pattern.matcher(registrationNumber);
        check = this.matcher.find();
    }

    public boolean check() {
        return check;
    }
}
