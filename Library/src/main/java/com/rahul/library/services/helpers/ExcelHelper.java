package com.rahul.library.services.helpers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rahul.library.entities.Author;

public class ExcelHelper {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Author> listAuthors;

	// constructor to initialize listAuthors and workBook
	public ExcelHelper(List<Author> listAuthors) {
		this.listAuthors = listAuthors;
		workbook = new XSSFWorkbook();
	}

	// Creating first main row with the following configurations
	private void writeHeaderLine() {
		sheet = workbook.createSheet("Authors");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "AuthorId", style);
		createCell(row, 1, "FirstName", style);
		createCell(row, 2, "LastName", style);
//	        createCell(row, 3, "Roles", style);
//	        createCell(row, 4, "Enabled", style);

	}

	// Create each cell/column for main row
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	// Create rows from the list coming from db
	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Author author : listAuthors) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, author.getId(), style);
			createCell(row, columnCount++, author.getFirstName(), style);
			createCell(row, columnCount++, author.getLastName(), style);
//	            createCell(row, columnCount++, author.getRoles().toString(), style);
//	            createCell(row, columnCount++, author.isEnabled(), style);

		}
	}

	public void export(HttpServletResponse response) throws IOException {
		// create main row of excel
		writeHeaderLine();
		// create other rows from db
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
