package ru.languagetrainer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
public class Word implements Externalizable {
    private String english;
    private String russian;

    public Word(String english, String russian) {
        this.english = english;
        this.russian = russian;
    }

    public Word() {
    }

    public String getEnglish() {
        return english;
    }

    public String getRussian() {
        return russian;
    }

    @Override
    public String toString() {
        return english + " - " + russian;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(english);
        out.writeUTF(russian);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        english = in.readUTF();
        russian = in.readUTF();
    }
}
