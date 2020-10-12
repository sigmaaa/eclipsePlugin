package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public final class JsonFileWritter {
	
	private static FileWriter fileWriter;
	private static String productJsonPath = "./products.json";
	
	public static void saveProductToFile(String productName, String productType, String description) throws IOException {
		
		File file = new File(productJsonPath);
		createFileIfNotExisting(file);
		JSONObject details = fulfillDetails(productName, productType, description);
		JSONObject product = fulfillProduct(details);
		JSONArray productList = getJsonArrayFromFile(product);
		productList.add(product);
		writeListToJsonFile(product, productList);
	}

	private static JSONObject fulfillProduct(JSONObject details) {
		JSONObject product = new JSONObject();
		product.put("product", details);
		return product;
	}

	private static JSONObject fulfillDetails(String productName, String productType, String description) {
		JSONObject details = new JSONObject();
		
		details.put("Name", productName);
		details.put("Type", productType);
		details.put("Description", description);
		return details;
	}

	private static void createFileIfNotExisting(File file) throws IOException {
		if (file.createNewFile()) {
			fileWriter = new FileWriter(productJsonPath);
			fileWriter.write("[]");
			fileWriter.flush();
			fileWriter.close();
            System.out.println("products.json has been created with empty array.");
        } else {
            System.out.println("products.json already exists.");
        }
	}

	private static void writeListToJsonFile(JSONObject product, JSONArray productList) {
		try {
	            fileWriter = new FileWriter(productJsonPath);
	            fileWriter.write(productList.toJSONString());
	            System.out.println("Successfully Copied JSON Object to File...");
	            System.out.println("\nJSON Object: " + product);
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	 
	        } finally {
	 
	            try {
	            	fileWriter.flush();
	                fileWriter.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	                }
	        }
	}

	private static JSONArray getJsonArrayFromFile(JSONObject product) {
		JSONArray productList = new JSONArray();
		try {
			productList = JsonParser.getArrayListFromFile(productJsonPath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return productList;
	}
}
