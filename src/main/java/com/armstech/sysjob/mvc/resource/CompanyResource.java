package com.armstech.sysjob.mvc.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armstech.sysjob.api.event.ResourceCreateEvent;
import com.armstech.sysjob.mvc.model.Company;
import com.armstech.sysjob.mvc.model.filter.CompanyFilter;
import com.armstech.sysjob.mvc.service.CompanyServicempl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/companys")
public class CompanyResource {

	@Autowired
	private CompanyServicempl companyService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<List<Company>> getCompanys() {
		List<Company> listCompany = companyService.getCompanys();
		if (!listCompany.isEmpty()) {
			return ResponseEntity.ok(listCompany);
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<List<Company>> getCompanysByFilter(CompanyFilter companyFilter) {
		List<Company> listCompany = companyService.getCompanysByFilter(companyFilter);
		if (!listCompany.isEmpty()) {
			return ResponseEntity.ok(listCompany);
		}
		return ResponseEntity.notFound().build();
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
		Company company = companyService.getCompanyById(id);
		return company != null ? ResponseEntity.ok(company) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Company> save(@Valid @RequestBody Company company, HttpServletResponse response) {
		company = companyService.save(company);
		publisher.publishEvent(new ResourceCreateEvent(this, response, company.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(company);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Company> update(@PathVariable Long id, @Valid @RequestBody Company company,
			HttpServletResponse response) {
		Company companypdated = companyService.update(id, company);
		publisher.publishEvent(new ResourceCreateEvent(this, response, companypdated.getId()));
		return ResponseEntity.ok(company);
	}

}
