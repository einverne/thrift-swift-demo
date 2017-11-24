package info.einverne.springmvcdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Created by mi on 17-11-24.
 */
public class JacksonTest {

    @Test
    public void encode() throws ParseException, JsonProcessingException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        ActorGson rudyYoungblood = new ActorGson("nm2199632", sdf.parse("21-09-1982"), Arrays.asList("Apocalypto", "Beatdown", "Wind Walkers"));
        Movie movie = new Movie("tt0472043", "Mel Gibson", Arrays.asList(rudyYoungblood));
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(movie);
        System.out.println(jsonResult);
    }

    @Test
    public void customEncode() throws ParseException, JsonProcessingException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        ActorGson blood = new ActorGson("nm1222", sdf.parse("21-09-1921"), Arrays.asList("A", "B", "C"));
        Movie movie = new Movie(null, "Mel Gibson", Arrays.asList(blood));

        SimpleModule module = new SimpleModule();
        module.addSerializer(new ActorJacksonSerializer(ActorGson.class));
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.registerModule(module)
                .writer(new DefaultPrettyPrinter())
                .writeValueAsString(movie);
        System.out.println(jsonResult);
    }

    @Test
    public void customDecode() throws IOException {
        String jsonStr = "{\n" +
                "  \"imdbId\": \"tt0472043\",\n" +
                "  \"director\": \"Mel Gibson\",\n" +
                "  \"actors\": [\n" +
                "    {\n" +
                "      \"imdbId\": \"nm2199632\",\n" +
                "      \"dateOfBirth\": 401385600000,\n" +
                "      \"filmography\": [\n" +
                "        \"Apocalypto\",\n" +
                "        \"Beatdown\",\n" +
                "        \"Wind Walkers\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        mapper.setDateFormat(df);

        Movie movie = mapper.readValue(jsonStr, Movie.class);
        System.out.println(movie);
    }
}
