package vadim.volin.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class ProjectUtils {

    public static Map<Integer, String> getTasksData(String JSON_TASKS) throws IOException {
        Map<Integer, String> columnsData = new TreeMap<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        if (JSON_TASKS == null || JSON_TASKS.equals("")) {
            return columnsData;
        }
        JsonParser parser = factory.createParser(JSON_TASKS);
        JsonNode rootObject = mapper.readTree(parser);

        ArrayNode columnArray = (ArrayNode) rootObject.get("columns").get("items");
        for (int i = 0; i < columnArray.size(); i++) {
            String columnTitle = columnArray.get(i).get("title").textValue().replaceAll("(\\\\r\\\\n|\\\\r|\\\\n|\\s{2,})", " ");
            Integer notesCount = columnArray.get(i).get("notesId").size();
            columnsData.put(notesCount, columnTitle);
        }
        return columnsData;
    }

    public static int getTaskCount(String JSON_TASKS) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        if (JSON_TASKS == null || JSON_TASKS.equals("")) {
            return 0;
        }
        JsonParser parser = factory.createParser(JSON_TASKS);
        JsonNode rootObject = mapper.readTree(parser);

        ArrayNode columnArray = (ArrayNode) rootObject.get("notes").get("items");

        return columnArray.size();
    }

}
