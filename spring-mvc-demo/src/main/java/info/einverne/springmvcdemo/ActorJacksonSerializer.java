package info.einverne.springmvcdemo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

/**
 * Created by mi on 17-11-24.
 */
public class ActorJacksonSerializer extends StdSerializer<ActorGson> {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    protected ActorJacksonSerializer(Class<ActorGson> t) {
        super(t);
    }

    @Override
    public void serialize(ActorGson actor, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("ibdbId", actor.getImdbId());
        jsonGenerator.writeObjectField("dateOfBirth",
                actor.getDateOfBirth() != null ? sdf.format(actor.getDateOfBirth()) : null);
        jsonGenerator.writeNumberField("N Film:", actor.getFilmography() != null ? actor.getFilmography().size() : 0);
        jsonGenerator.writeStringField("filmography", actor.getFilmography().stream().collect(Collectors.joining("-")));
        jsonGenerator.writeEndObject();
    }
}
