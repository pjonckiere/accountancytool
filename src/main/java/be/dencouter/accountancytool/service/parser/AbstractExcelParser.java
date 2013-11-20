package be.dencouter.accountancytool.service.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import be.dencouter.accountancytool.model.ExcelRow;

/**
 * Superclass for Excel parser implementation, that contain shared behaviour.
 */
public abstract class AbstractExcelParser implements ExcelParser {

	/**
	 * Read a file from a certain location and return its first sheet.
	 * 
	 * @param fileLocation
	 *            The location of the file.
	 * @return The first worksheet (index = 0)
	 */
	protected HSSFSheet getSheet(String fileLocation) {
		HSSFSheet sheet = null;
		try {
			// open a file input stream
			FileInputStream file = new FileInputStream(new File(fileLocation));

			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			sheet = workbook.getSheetAt(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sheet;
	}

	/**
	 * Create an {@link ExcelRow} instance.
	 * 
	 * @param id
	 * @param date
	 * @param amount
	 * @param description
	 * @return the {@link ExcelRow} object
	 */
	public ExcelRow createExcelRowInstance(int id, String date, BigDecimal amount, String description) {
		ExcelRow excelRow = new ExcelRow();
		excelRow.setRowId(id);
		excelRow.setDate(date);
		excelRow.setAmount(amount);
		excelRow.setDescription(description);
		return excelRow;
	}

	/**
	 * Abstract definition of the parse method. Extending classes should
	 * implement this method to parse the string.
	 */
	public abstract List<ExcelRow> parse(String fileLocation);
}
