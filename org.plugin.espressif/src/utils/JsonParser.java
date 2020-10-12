package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Product;

public final class JsonParser {
	
	private JsonParser() {}
	private static String productJsonPath = "./products.json";
	
	public static ArrayList<Product> getAllProductsFromJson() {
		ArrayList<Product> allItems = new ArrayList<>();
		fulfillItemsFromJsonFile(allItems);
		return allItems;
	}

	private static void fulfillItemsFromJsonFile(ArrayList<Product> allItems) {
		JSONArray jsoncontent;
		try {
			jsoncontent = getArrayListFromFile(productJsonPath);
			for (int i = 0; i < jsoncontent.size(); i++) {
				JSONObject obj = (JSONObject) jsoncontent.get(i);
				obj = (JSONObject) obj.get("product");
				Product product = new Product((String) obj.get("Name"),(String) obj.get("Type"),(String) obj.get("Description"));
				allItems.add(product);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static JSONArray getArrayListFromFile(String path) throws IOException, ParseException {
		
		try (FileReader reader = new FileReader(path)) {
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(reader);
			JSONArray jsonContent = (JSONArray) obj;
			return jsonContent;
		}
	}

}
