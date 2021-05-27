public class Config {
    public static final String BASE_URL = "https://api.lexigram.io/v1";
    public static final String SEARCH = "/lexigraph/search/?q={label}&limit=20";
    public static final String API_KEY = "api-key";
    public static final String CONCEPT_ID = "/lexigraph/concepts/{concept_id}";
    public static final String ENTITY = "/extract/entities";
    public static final String JSON_BODY = "{\n" +
            "\"text\": \"The patient has diabetes\",\n" +
            "\"withContext\": true,\n" +
            "\"withMatchLogic\": \"ignore-length\",\n" +
            "\"withText\": true\n" +
            "}";
    public static final String HIGHLIGHT = "/highlight/entities";
}
