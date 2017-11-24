package info.einverne.springmvcdemo;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by mi on 17-11-24.
 */
public class ActorGsonDeserializer implements JsonDeserializer<ActorGson> {
    private SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");

    @Override
    public ActorGson deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        JsonElement jsonImdbId = jsonObject.get("imdbId");
        JsonElement jsonDateOfBirth = jsonObject.get("dateOfBirth");
        JsonArray jsonFilmography = jsonObject.getAsJsonArray("filmography");

        ArrayList<String> filmList = new ArrayList<>();
        if (jsonFilmography != null) {
            for (int i = 0; i < jsonFilmography.size(); i++) {
                filmList.add(jsonFilmography.get(i).getAsString());
            }
        }

        ActorGson actorGson = null;
        try {
            actorGson = new ActorGson(jsonImdbId.getAsString(), sdf.parse(jsonDateOfBirth.getAsString()), filmList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return actorGson;
    }
}
