package ru.languagetrainer.data_bases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.languagetrainer.models.Word;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseJson implements DataBase<Word> {
    private List<Word> words = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String path = "" +
            "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "db.json";

    /**
     * @return List<Word> которые записаны в json-file
     */
    @Override
    public List<Word> getAll() {
        File file = new File(path);

        try {
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Word.class));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return words;
    }

    @Override
    public void add(Word word) {
        words = getAll();
        words.add(word);

        try {
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.writeValue(new File(path), words);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
