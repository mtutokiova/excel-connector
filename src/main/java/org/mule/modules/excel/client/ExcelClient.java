package org.mule.modules.excel.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExcelClient {

	public void createExcel() throws IOException {

		String json = new String(Files.readAllBytes(Paths.get("src/test/java","example.txt")));

		JsonRequest jsonRequest = new ObjectMapper().readValue(json, JsonRequest.class);

		FileOutputStream out = new FileOutputStream("src/test/java/sxssf.xlsx");

		// keep 100 rows in memory, exceeding rows will be flushed to disk
		SXSSFWorkbook wb = new SXSSFWorkbook(100); 
		for (JsonRequestSheet requestSheet : jsonRequest.getSheets()) {

			Sheet sh = wb.createSheet(requestSheet.getSheetName());
			
			// creates column names
			Row row = sh.createRow(0);
			int cellnum = 0;
			for (String columnNames : requestSheet.getSheetBody().get(0).keySet()) {
				Cell cell = row.createCell(cellnum++);
				cell.setCellValue(columnNames);
			};
			
			// created table
			int rownum = 1;
			for (Map<String, String> sheetBody : requestSheet.getSheetBody()) {
				row = sh.createRow(rownum++);
				
				cellnum = 0;
				for (Entry<String, String> column : sheetBody.entrySet()) {
					Cell cell = row.createCell(cellnum++);
					cell.setCellValue(column.getValue());
				}
			}

		}
		wb.write(out);
		out.close();
		// dispose of temporary files backing this workbook on disk
		wb.dispose();
		wb.close();

	}
	
	public static void main(String[] args) throws IOException {
		ExcelClient client = new ExcelClient();
		client.createExcel();
	}

}
