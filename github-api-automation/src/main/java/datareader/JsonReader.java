package datareader;

import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader
{

    private final String TEST_DATA_PATH = "src/test/resources/test-data/";
    String jsonReader;
    String jsonFileName;

    public JsonReader(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
//            LogsManager.error("Error loading json file:"+ jsonFileName, e.getMessage());
            System.out.println("Error loading json file:"+ jsonFileName + " - " + e.getMessage());
            jsonReader = "{}"; // Initialize to an empty JSON object to avoid null pointer exceptions
        }
    }

    public String getJsonData(String jsonPath) {
        try {
            return JsonPath.from(jsonReader).getString(jsonPath);
        } catch (Exception e) {
            System.out.println("Error reading json data with path: " + jsonPath + " - " + e.getMessage());
            return "";
        }
    }
}
