package info.einverne.springmvcdemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Created by mi on 17-11-24.
 */
public class GsonTest {

    @Test
    public void javaToJson() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        ActorGson rudyYoungblood = new ActorGson("nm2199632", sdf.parse("21-09-1982"), Arrays.asList("Apocalypto", "Beatdown", "Wind Walkers"));
        Movie movie = new Movie("tt0472043", "Mel Gibson", Arrays.asList(rudyYoungblood));
        String serializedMovie = new Gson().toJson(movie);
        System.out.println(serializedMovie);
    }

    /**
     * 结果是格式化内容
     * 属性可以自定义，添加HTML属性
     * null 结果允许包含在内
     * Date 自定义
     * 添加新属性 N° Film
     * filmography 被格式化字符串，而不是默认的 List
     *
     * @throws ParseException
     */
    @Test
    public void withNull() throws ParseException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .disableHtmlEscaping()
                .registerTypeAdapter(ActorGson.class, new ActorGsonSerialier())
                .create();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        ActorGson rudyYoungblood = new ActorGson("nm2199632", sdf.parse("21-09-1982"), Arrays.asList("Apocalypto", "Beatdown", "Wind Walkers"));
        Movie movieWithNullValue = new Movie(null, "Mel Gibson", Arrays.asList(rudyYoungblood));
        String serializedMovie = gson.toJson(movieWithNullValue);
        System.out.println(serializedMovie);
    }

    @Test
    public void deserialize() {
        String jsonInput = "{\n" +
                "  \"imdbId\": \"tt0472043\",\n" +
                "  \"director\": \"Mel Gibson\",\n" +
                "  \"actors\": [\n" +
                "    {\n" +
                "      \"imdbId\": \"nm2199632\",\n" +
                "      \"dateOfBirth\": \"Sep 21, 1982 12:00:00 AM\",\n" +
                "      \"filmography\": [\n" +
                "        \"Apocalypto\",\n" +
                "        \"Beatdown\",\n" +
                "        \"Wind Walkers\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ActorGson.class, new ActorGsonDeserializer())
                .create();

        Movie output = gson.fromJson(jsonInput, Movie.class);
        System.out.println(output);
    }
}
