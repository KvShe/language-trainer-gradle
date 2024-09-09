package ru.languagetrainer.controllers;

import ru.languagetrainer.data_bases.DataBase;
import ru.languagetrainer.data_bases.DataBaseJson;
import ru.languagetrainer.models.Word;
import ru.languagetrainer.view.ConsoleView;
import ru.languagetrainer.view.View;

public class Controller {
//    private final DataBase<Word> dataBase = new DataBaseList();
    private final DataBase<Word> dataBase = new DataBaseJson();
    private final View view = new ConsoleView(dataBase);

    public void run() {
        view.showMenu();
    }
}
