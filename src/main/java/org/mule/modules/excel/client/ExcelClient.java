package org.mule.modules.excel.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.mule.modules.excel.entities.JsonRequest;
import org.mule.modules.excel.entities.JsonRequestSheet;
import org.mule.modules.excel.exception.ExcelConnectorException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExcelClient {

	/**
	 * Creates excel file from the given input json.
	 * 
	 * @param json
	 * @param fileName name of the output excel file
	 * @param filePath path to the output excel file
	 * @param appendCurrentDate true if current date should be appended to fileName
	 * @throws ExcelConnectorException when there is an issue with the input json file or an exception with the response file
	 */
	public void createExcel(String json, String fileName, String filePath, Boolean appendCurrentDate) throws ExcelConnectorException {

		try {
			JsonRequest jsonRequest = new ObjectMapper().readValue(json, JsonRequest.class);

			FileOutputStream out = new FileOutputStream(filePath +"/" + fileName + (appendCurrentDate ? new SimpleDateFormat("_yyyy_MM_dd").format(new Date()) : "") + ".xlsx");

			// keep 100 rows in memory, exceeding rows will be flushed to disk
			SXSSFWorkbook wb = new SXSSFWorkbook(100);
			for (JsonRequestSheet requestSheet : jsonRequest.getSheets()) {

				Sheet excelSheet = wb.createSheet(requestSheet.getSheetName());

				if(!requestSheet.getSheetBody().isEmpty()){
					createTableHeader(requestSheet, excelSheet);
					fillTableRows(requestSheet, excelSheet);
				}
			}
			wb.write(out);
			out.close();
			// dispose of temporary files backing this workbook on disk
			wb.dispose();
			wb.close();

		} catch (IOException exception) {
			throw new ExcelConnectorException(exception.getMessage());
		}
	}
	
	/////////////
	/// UTILS ///
	/////////////
	
	/** Create column names as first row in a table */
	private void createTableHeader(JsonRequestSheet requestSheet, Sheet excelSheet) {
		Row row = excelSheet.createRow(0);
		int cellnum = 0;
		for (String columnNames : requestSheet.getSheetBody().get(0).keySet()) {
			Cell cell = row.createCell(cellnum++);
			cell.setCellValue(columnNames);
		}
	}

	/** Fill the table body with values from the request */
	private void fillTableRows(JsonRequestSheet requestSheet, Sheet excelSheet) {
		int rownum = 1;
		for (Map<String, String> sheetBody : requestSheet.getSheetBody()) {
			Row row = excelSheet.createRow(rownum++);

			int cellnum = 0;
			for (Entry<String, String> column : sheetBody.entrySet()) {
				Cell cell = row.createCell(cellnum++);
				cell.setCellValue(column.getValue());
			}
		}
	}

//	public static void main(String[] args) throws ExcelConnectorException, IOException {
//		ExcelClient client = new ExcelClient();
//		client.createExcel(new String(Files.readAllBytes(Paths.get("src/test/java", "example.txt"))), "response", "src/test/java/", true);
//	}

}
