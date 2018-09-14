package com.armstech.sysjob.mvc.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.armstech.sysjob.api.event.ResourceCreateEvent;
import com.armstech.sysjob.api.exception.CompanyNotFoudException;
import com.armstech.sysjob.api.exception.ExceptionHandlerSpring.Error;
import com.armstech.sysjob.mvc.model.Vacancy;
import com.armstech.sysjob.mvc.model.filter.VacancyFilter;
import com.armstech.sysjob.mvc.service.VacancyService;

@RestController("/vacancys")
public class VacancyResource {

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private VacancyService vacancyService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public ResponseEntity<List<Vacancy>> getVacancys() {
		List<Vacancy> listVacancy = vacancyService.getVacancys();
		if (!listVacancy.isEmpty()) {
			return ResponseEntity.ok(listVacancy);
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<List<Vacancy>> getVacancysByFilter(VacancyFilter vacancyFilter) {
		List<Vacancy> listVacancy = vacancyService.getVacancysByFilter(vacancyFilter);
		if (!listVacancy.isEmpty()) {
			return ResponseEntity.ok(listVacancy);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Vacancy> getVacancyById(@PathVariable Long id) {
		Vacancy company = vacancyService.getVacancyById(id);
		return company != null ? ResponseEntity.ok(company) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Vacancy> save(@Valid @RequestBody Vacancy vacancy, HttpServletResponse response) {
		vacancy = vacancyService.save(vacancy);
		publisher.publishEvent(new ResourceCreateEvent(this, response, vacancy.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(vacancy);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Vacancy> update(@PathVariable Long id, @Valid @RequestBody Vacancy vacancy,
			HttpServletResponse response) {
		Vacancy companyUpdated = vacancyService.update(id, vacancy);
		return ResponseEntity.ok(companyUpdated);
	}

	@ExceptionHandler({ CompanyNotFoudException.class })
	public ResponseEntity<Object> handleComapnyNotFoundException(CompanyNotFoudException ex) {
		String msgUser = messageSource.getMessage("msg.company.nao.existe.inativa", null,
				LocaleContextHolder.getLocale());
		String msgDeveloper = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> listErros = Arrays.asList(new Error(msgUser, msgDeveloper));
		return ResponseEntity.badRequest().body(listErros);
	}

}
