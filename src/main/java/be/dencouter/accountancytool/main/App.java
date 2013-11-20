package be.dencouter.accountancytool.main;

import java.util.*;

import be.dencouter.accountancytool.model.ExcelRow;
import be.dencouter.accountancytool.service.parser.AssistExcelParser;
import be.dencouter.accountancytool.service.parser.KbcExcelParser;

/**
 * The app class contains the execution logic of the full application. It also
 * contains the main method.
 */
public class App {

	private static final String ASSIST_LOCATION = "/home/pieter/Documents/jh/excel/Assist_Verrichtingen_2013_conv.xls";
	private static final String KBC_LOCATION = "/home/pieter/Documents/jh/excel/KBC_Verrichtingen_2013.csv";
	
	/**
	 * The main method gets executed on startup of the program. It contains all
	 * routines that should be done while running the accountancytool.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.println("Accountancy commandline tool for JH Den Couter");

		// parse assist and kbc excel to rows
		System.out.println("Parsing files");
		List<ExcelRow> assistRows = (new AssistExcelParser()).parse(ASSIST_LOCATION);
		List<ExcelRow> kbcRows = (new KbcExcelParser()).parse(KBC_LOCATION);
		ArrayList<ExcelRow> assistMissingRows = new ArrayList<ExcelRow>();
		List<ExcelRow> kbcMissingRows = new ArrayList<ExcelRow>();

		for(ExcelRow xr : assistRows) {
			assistMissingRows.add(xr.clone());
		}

		// merge "stortingen"
		for (int i = 0 ; i < kbcRows.size() - 1 ; i++) {
			if(kbcRows.get(i).getDescription().contains("STORTING")
				&& kbcRows.get(i+1).getDescription().contains("STORTING")) {			
				kbcRows.get(i+1).setAmount(kbcRows.get(i).getAmount().add(kbcRows.get(i+1).getAmount()));
				kbcRows.remove(i);
				i--;
				// System.out.println("Stortingen merged");
			} else if(kbcRows.get(i).getDescription().contains("GELDOPNEMING")
				&& kbcRows.get(i+1).getDescription().contains("GELDOPNEMING")) {	
				kbcRows.get(i+1).setAmount(kbcRows.get(i).getAmount().subtract(kbcRows.get(i+1).getAmount()));
				kbcRows.remove(i);
				i--;
				// System.out.println("Geldopnemingen merged");
			}
		}

		// compare the result of both excel files
		System.out.println("Comparing rows for both Excel files");
		int hits = 0;
		for( int i = 0 ; i < 206 ; i++) {
			int index = assistRows.indexOf(kbcRows.get(i));
			if(index != -1 ){
				assistMissingRows.remove(assistRows.get(i));
				hits++;
			}
			else {
				kbcMissingRows.add(kbcRows.get(i));
			}
		}
		
		// display execution stats
		System.out.println("Hits: " + hits);
		System.out.println(String.format("Assist Excel rows %d", assistRows.size()));
		System.out.println(String.format("KBC Excel rows %d", kbcRows.size()));
		System.out.println(String.format("Assist Excel missing rows %d", assistMissingRows.size()));
		System.out.println(String.format("KBC Excel missing rows %d", kbcMissingRows.size()));
		
		/*
		for( int j = 0 ; j < kbcMissingRows.size() ; j++) {
			System.out.println(assistMissingRows.get(j));
			System.out.println(kbcMissingRows.get(j));
		}
		*/
		
		long endTime = System.currentTimeMillis();
		long executionTime = (endTime - startTime) / 1000;
		System.out.println(String.format("Execution time %ds", executionTime));
	}
}
