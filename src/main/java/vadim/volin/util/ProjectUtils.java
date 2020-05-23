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
        JsonParser parser = factory.createParser(JSON_TASKS);
        JsonNode rootObject = mapper.readTree(parser);
//        {
//          "columns":
//              {"idCounter":2,"title":"",
//              "items":[
//                  {"id":1,"title":"manluck-c1","notesId":[1,2,6,4,5,7,9]},
//                  {"id":2,"title":"manluck-c2","notesId":[3,8,10]}]}
//               ,"notes":{"idCounter":10,"items":[{"id":1,"content":"m1"},{"id":2,"content":"m2"},{"id":6,"content":"m6"},{"id":4,"content":"m4"},{"id":5,"content":"m5"},{"id":7,"content":"m7"},{"id":9,"content":"m10"},{"id":3,"content":"m3"},{"id":8,"content":"m8"},{"id":10,"content":"m9"}]}}

        ArrayNode columnArray = (ArrayNode) rootObject.get("columns").get("items");
        for (int i = 0; i < columnArray.size(); i++) {
            String columnTitle = columnArray.get(i).get("title").textValue().replaceAll("(\\\\r\\\\n|\\\\r|\\\\n|\\s{2,})", " ");
            Integer notesCount = columnArray.get(i).get("notesId").size();
            columnsData.put(notesCount, columnTitle);
        }
        return columnsData;
    }

}
