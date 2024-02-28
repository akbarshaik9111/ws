package com.app.akbar.service.impl;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.app.akbar.entity.CitizenPlan;
import com.app.akbar.repo.CitizenPlanRepository;
import com.app.akbar.request.SearchRequest;
import com.app.akbar.service.ReportService;
import com.app.akbar.util.EmailUtils;
import com.app.akbar.util.ExcelGenerator;
import com.app.akbar.util.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository repo;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public List<String> getPlanNames() {
		return repo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return repo.getPlanStatuses();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		
		if(null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		
		if(null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		if(null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		if(null != request.getPlanStartDate() && !"".equals(request.getPlanStartDate())) {
			String startDate = request.getPlanStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			  

			  //convert String to LocalDate
			  LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}
		
		if(null != request.getPlanEndDate() && !"".equals(request.getPlanEndDate())) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			  String endDate = request.getPlanEndDate();

			  //convert String to LocalDate
			  LocalDate localDate = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localDate);
		}
		
		return repo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		File file = new File("plans.xls");
		List<CitizenPlan> plans = repo.findAll();
		
		excelGenerator.generate(response, plans, file);
		
		String subject = "Excel Insurance Report";
		String body = "<h2>WELCOME TO EXCEL INSURNACE GENERATE</h2>";
		String to = "akbarshaik9111@gmail.com";
		
		emailUtils.sendEmail(subject, body, to, file);
		file.delete();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		List<CitizenPlan> plans = repo.findAll();
		File file = new File("Plans.pdf");
		
		pdfGenerator.generate(response, plans, file);
		
		String subject = "Excel Insurance Report";
		String body = "<h2>WELCOME TO PDF INSURNACE GENERATE</h2>";
		String to = "akbarshaik9111@gmail.com";
		
		emailUtils.sendEmail(subject, body, to, file);
		file.delete();
		return true;
	}
}