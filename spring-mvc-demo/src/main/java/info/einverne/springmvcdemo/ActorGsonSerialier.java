package info.einverne.springmvcdemo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mi on 17-11-24.
 */
public class ActorGsonSerialier implements JsonSerializer<ActorGson> {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public JsonElement serialize(ActorGson actor, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject actorJsonObj = new JsonObject();

        actorJsonObj.addProperty("<strong>IMDBCode</strong>", actor.getImdbId());

        actorJsonObj.addProperty("<strong>DateOfBirth</strong>",
                actor.getDateOfBirth() != null ?
                        sdf.format(actor.getDateOfBirth()) : null);

        actorJsonObj.addProperty("<strong>N°Film:</strong>", actor.getFilmography() != null ? actor.getFilmography().size() : null);

        actorJsonObj.addProperty("filmography", actor.getFilmography() != null ?
                convertFilmography(actor.getFilmography()) : null);

        return actorJsonObj;

    }

    private String convertFilmography(List<String> filmography) {
        return filmography.stream()
                .collect(Collectors.joining("-"));
    }
}
