package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    HashMap<String, Json> jsonPairs = new HashMap<String, Json>();
    public JsonObject(JsonPair... jsonPairs) {
        for(int i = 0; i<jsonPairs.length; i++){
            JsonPair curPair = jsonPairs[i];
            this.jsonPairs.put(curPair.key, curPair.value);
        }
    }

    @Override
    public String toJson() {
        StringBuilder jsonStr = new StringBuilder();
        Iterator<Map.Entry<String,Json>> jsonIterator = jsonPairs.entrySet().iterator();
        jsonStr.append("{");
        while (jsonIterator.hasNext()) {
            Map.Entry<String,Json> json = jsonIterator.next();
            jsonStr.append("'");
            jsonStr.append(json.getKey());
            jsonStr.append("'");
            jsonStr.append(": ");
            jsonStr.append(json.getValue().toJson());
            if (jsonIterator.hasNext()) {
                jsonStr.append(", ");
            }
        }
        jsonStr.append("}");
        return jsonStr.toString();
    }

    public void add(JsonPair jsonPair) {
        jsonPairs.put(jsonPair.key, jsonPair.value);
    }

    public boolean contains(String name){
        if(jsonPairs.get(name) == null) return false;
        return true;
    }

    public Json find(String name) {
        return jsonPairs.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObject = new JsonObject();
        for(String name: names){
            if(contains(name)) jsonObject.add(new JsonPair(name, jsonPairs.get(name)));
        }
        return jsonObject;
    }
}
