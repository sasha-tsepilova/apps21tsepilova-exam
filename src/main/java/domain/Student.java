package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private List<Tuple<String,Integer>> exams;
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name,surname,year);
        this.exams = Arrays.asList(exams);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject withoutExams = super.toJsonObject();
        JsonObject[] exam = new JsonObject[exams.size()];
        for(int i = 0 ; i < exams.size(); i++){
            Tuple<String, Integer> tuple = exams.get(i);
            exam[i] = new JsonObject(new JsonPair("course", new JsonString(tuple.key)),
                    new JsonPair("mark", new JsonNumber(tuple.value)),
                    new JsonPair("passed", new JsonBoolean(tuple.value > 2)));
        }
        withoutExams.add(new JsonPair("exams", new JsonArray(exam)));
        return withoutExams;
    }
}