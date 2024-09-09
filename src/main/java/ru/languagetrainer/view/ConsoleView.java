package ru.languagetrainer.view;

import ru.languagetrainer.data_bases.DataBase;
import ru.languagetrainer.models.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View {
    private final DataBase<Word> dataBase;
    private List<Word> errors;
    private final Scanner scanner;

    public ConsoleView(DataBase<Word> dataBase) {
        this.dataBase = dataBase;
        this.errors = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void index() {
        dataBase.getAll().forEach(System.out::println);
    }

    @Override
    public void showMenu() {
        while (true) {
            printMenu();
            switch (scanner.nextLine()) {
                case "1" -> trainer();
                case "2" -> index();
                case "3" -> addNewWord();
                case "0" -> System.exit(0);
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void printMenu() {
        System.out.println("- - - - - - - - - -");
        System.out.println("1 - Start training");
        System.out.println("2 - Show all words");
        System.out.println("3 - Add a new word");
        System.out.println("0 - Exit");
        System.out.print("Select an action: ");
    }

    private void trainer() {
        List<Word> words = dataBase.getAll();
        Collections.shuffle(words);

        for (Word word : words) {
            String input = getInput(word);

            if (!check(input, word)) {
                addInErrorList(word);
                showError(input, word);
            }
        }

        while (isError()) {
            Collections.shuffle(errors);

            List<Word> temp = new ArrayList<>();

            for (Word word : errors) {
                String input = getInput(word);

                if (!check(input, word)) {
                    temp.add(word);
                    showError(input, word);
                }
            }

            errors = temp;
        }

        showMenu();
    }

    private void addInErrorList(Word word) {
        errors.add(word);
    }

    private boolean isError() {
        return !errors.isEmpty();
    }

    private boolean check(String input, Word word) {
        return input.equalsIgnoreCase(word.getEnglish());
    }

    private void showError(String input, Word word) {
        System.out.println();
        System.out.println(input + " - " + word.getEnglish());
        System.out.println();
    }

    private void addNewWord() {
        System.out.print("Enter a new word english: ");
        String english = scanner.nextLine().trim();
        System.out.print("Enter a new word russian: ");
        String russian = scanner.nextLine().trim();

        Word word = new Word(english, russian);
        dataBase.add(word);

        System.out.println("New word: " + word + " successfully added");
    }

    private String getInput(Word word) {
        System.out.print(word.getRussian() + " - ");
        return scanner.nextLine()
                .replaceAll("\\p{P}", "")
                .replaceAll("\\+", "")
                .replaceAll("=", "")
                .replaceAll(" {2}", " ")
                .trim();
    }
}
