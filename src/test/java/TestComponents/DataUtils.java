package TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataUtils {

    public HashMap<String, String> getNextUnusedData() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/java/Data/CustomerData.json";

        String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String, Object>> data =
                mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, Object>>>() {});

        for (HashMap<String, Object> entry : data) {

            Boolean used = (Boolean) entry.get("used");

            if (!used) {
                entry.put("used", true); // mark as used

                // write back to JSON
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), data);

                // return only needed fields
                HashMap<String, String> result = new HashMap<>();
                result.put("customerId", (String) entry.get("customerId"));
                result.put("postalCode", (String) entry.get("postalCode"));

                return result;
            }
        }

        throw new RuntimeException("No unused data available");
    }
}
