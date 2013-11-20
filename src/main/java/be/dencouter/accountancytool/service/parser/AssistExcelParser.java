package be.dencouter.accountancytool.service.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import be.dencouter.accountancytool.model.ExcelRow;

/**
 * The {@link AssistExcelParser} converts an Assist Excel file to a list of
 * {@link ExcelRow} objects.
 */
public class AssistExcelParser extends AbstractExcelParser {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ExcelRow> parse(String fileLocation) {
		List<ExcelRow> rows = new ArrayList<ExcelRow>();

		HSSFSheet sheet = getSheet(fileLocation);

		// Create all ExcelRow objects. Start with index = 2 since the first row
		// are textual headers
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			HSSFRow currentRow = sheet.getRow(i);
			if (currentRow.getCell(14).toString().equalsIgnoreCase("Zichtrekening")) {
				int id = Integer.valueOf(currentRow.getCell(0).getStringCellValue());
				String date = currentRow.getCell(2).getStringCellValue();
				
				BigDecimal amount = BigDecimal.valueOf(currentRow.getCell(5).getNumericCellValue());	
				if (currentRow.getCell(11).toString().equals("Uitgaven")) {
					amount = amount.multiply(new BigDecimal("-1"));
				}
				
				String description = currentRow.getCell(6).getStringCellValue();
				rows.add(createExcelRowInstance(id, date, amount, description));
			}
		}

		return rows;
	}

}
