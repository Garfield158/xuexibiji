import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xiongs.dataway.model.ReportBean;

import java.io.IOException;
import java.util.*;

public class test {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        String jsonstr = "{\n" +
                "        \"5B0A1AB363FE3DDE8412B66868B9FDD7\": {\n" +
                "            \"1407379178651656\": {\n" +
                "                \"last_collect_time\": 1589859350000,\n" +
                "                \"last_collect_data\": \"38.40\"\n" +
                "            },\n" +
                "            \"1407379178586113\": {\n" +
                "                \"last_collect_time\": 1589859649000,\n" +
                "                \"last_collect_data\": \"19.00\"\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        try {
            test(jsonstr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //collection(jsonstr);
    }

    public static void collection(String jsonstr) {

        JSONObject jsonObject = JSONObject.parseObject(jsonstr);
        Set<String> set = jsonObject.keySet();
        List<ReportBean> reportBeans = new ArrayList<>();
        for (String key : set) {
            ReportBean reportBean = new ReportBean();
            Object value = jsonObject.get(key);
            reportBean.setValue(value);
            //key由数据库获取
            reportBean.setQuotaCnName(key);
            if (value instanceof String)
                reportBean.setDataType("String");
            if (value instanceof Integer)
                reportBean.setDataType("Integer");
            if (value instanceof JSONArray) {
                List<ReportBean> array = collectionObj2JSONArray(value);
                reportBean.setValue(array);
                reportBean.setDataType("List");
            }
            if (value instanceof Double)
                reportBean.setDataType("Double");

            reportBeans.add(reportBean);
        }
        report(reportBeans);
    }

    //数组存数组不支持
    private static List<ReportBean> collectionObj2JSONArray(Object value) {
        JSONArray array = (JSONArray) value;
        List<ReportBean> reportBeans = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            Set<String> set = obj.keySet();
            for (String key : set) {
                ReportBean reportBean = new ReportBean();
                Object v = obj.get(key);
                reportBean.setValue(value);
                //key由数据库获取
                reportBean.setQuotaCnName(key);
                if (v instanceof String)
                    reportBean.setDataType("String");
                if (v instanceof Integer)
                    reportBean.setDataType("Integer");
                if (v instanceof JSONArray) {
                    reportBean.setValue(collectionObj2JSONArray(value));
                    reportBean.setDataType("List");
                }
                if (v instanceof Double)
                    reportBean.setDataType("Double");

                reportBeans.add(reportBean);
            }
        }
        return reportBeans;
    }

    private static void report(List<ReportBean> reportBeans) {
        JSONObject object = new JSONObject();
        for (ReportBean reportBean : reportBeans) {
            //key 由数据库获取
            String key = reportBean.getQuotaCnName();
            String dataType = reportBean.getDataType();
            Object value = reportBean.getValue();
            if ("String".equals(dataType)) {
                object.put(key, (String) value);
            }
            if ("Integer".equals(dataType)) {
                object.put(key, (Integer) value);
            }
            if ("List".equals(dataType)) {
                object.put(key, reportList2JSONArray(value));
            }
        }
    }

    private static JSONArray reportList2JSONArray(Object value) {
        List<ReportBean> reportBeans = (List<ReportBean>) value;
        for (ReportBean r : reportBeans) {

        }
        System.out.println(reportBeans);
        return null;
    }

    private static void test(String jsonStr) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        JsonNode reportJSON = collectJSONO2ReportJSON(jsonNode,"物理机");
        System.out.println(reportJSON.toString());
    }

    public static JsonNode collectJSONO2ReportJSON(JsonNode jsonNode,String ciId) {
        if (jsonNode.isArray()) {
            ArrayNode arrayRs = objectMapper.createArrayNode();
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            for (JsonNode next : arrayNode) {
                arrayRs.add(collectJSONO2ReportJSON(next,ciId));
            }
            return arrayRs;
        } else if (jsonNode.isObject()) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            Iterator<String> names = jsonNode.fieldNames();
            while (names.hasNext()) {
                String k = names.next();
                JsonNode jsonNode1 = jsonNode.get(k);
                //此处的k通过数据库缓存获取
                objectNode.put(ciId+k, collectJSONO2ReportJSON(jsonNode1,ciId));
            }
            return objectNode;
        } else {
            return jsonNode;
        }
    }

}
