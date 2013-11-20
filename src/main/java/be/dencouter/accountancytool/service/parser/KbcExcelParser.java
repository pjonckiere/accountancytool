package be.dencouter.accountancytool.service.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;

import be.dencouter.accountancytool.model.ExcelRow;

/**
 * The {@link AssistExcelParser} converts a KBC Excel file to a list of
 * {@link ExcelRow} objects.
 */
public class KbcExcelParser extends AbstractExcelParser {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ExcelRow> parse(String fileLocation) {
		List<ExcelRow> rows = new ArrayList<ExcelRow>();

		try {
			// open a file input stream
			BufferedReader br = new BufferedReader(new FileReader(new File(fileLocation)));
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
			   // process the line.
			   if(line.startsWith("BE")) {
				   String[] parts = line.split(";");
				   String amountString = parts[8].replace(",", ".");
				   BigDecimal amount = new BigDecimal(amountString);
				   rows.add(createExcelRowInstance(i++, parts[5], amount, parts[6]));
			   }
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rows;
	}

}
