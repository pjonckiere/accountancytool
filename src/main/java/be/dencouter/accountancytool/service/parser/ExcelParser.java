package be.dencouter.accountancytool.service.parser;

import java.util.List;

import be.dencouter.accountancytool.model.ExcelRow;

/**
 * Interface defining the behavior of all parsers.
 */
public interface ExcelParser {

	/**
	 * The parse method reads the excel, parses it and converts it to 
	 * a list of {@link ExcelRow} entities.
	 * 
	 * @param fileLocation The location where the Excel file can be found.
	 * @return A list of {@link ExcelRow} entities.
	 */
	List<ExcelRow> parse(String fileLocation);
}
