package be.dencouter.accountancytool.model;

import java.math.BigDecimal;

import java.text.*;
import java.util.Date;

/**
 * The ExcelRow entity defines the common fields of all 
 * parsed excel files.
 */
public class ExcelRow {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");

	public int rowId;
	
	public String date;
	
	public BigDecimal amount;
	
	public String description;

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("%s\t\t\t%s\t\t\t%s", this.date, this.amount.toPlainString(), this.description);
	}
	
	@Override
	public boolean equals(Object obj) {
        ExcelRow rhs = (ExcelRow) obj;
        if(rhs.getAmount().compareTo(this.amount) != 0) {
			// System.out.println(String.format("Amounts are not equal for %s", this.description));
			return false;
		} 
		else {
			try {
				Date rhsDate = dateFormat.parse(rhs.getDate());
				Date thisDate = dateFormat.parse(this.date);
				if(!rhsDate.equals(thisDate)) {
					// System.out.println(String.format("Dates are not equal for %s", this.description));
					return false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}		
		}
        return true;
    }
    
    @Override
    public ExcelRow clone() {
		ExcelRow er = new ExcelRow();
		er.setRowId(this.rowId);
		er.setAmount(this.amount);
		er.setDate(this.date);
		er.setDescription(this.description);
		return er;
	}

}
