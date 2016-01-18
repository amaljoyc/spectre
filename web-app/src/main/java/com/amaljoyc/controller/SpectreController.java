package com.amaljoyc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.amaljoyc.model.BankAccount;
import com.amaljoyc.service.BankAccountService;

@Controller
public class SpectreController {

	private final static Logger LOGGER = LoggerFactory.getLogger(SpectreController.class);

	@Autowired
	BankAccountService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		LOGGER.info("Received request for index page");
		return "index";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<BankAccount> listAccounts() {
		LOGGER.info("Received request to list all bank accounts");
		return service.listAllAccounts();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteAccount(@RequestParam("id") int id) {
		LOGGER.info("Received request to delete bank account with id: {}", id);
		service.deleteAccount(id);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addAccount(@RequestParam("iban") String iban, @RequestParam("bic") String bic) {
		BankAccount account = new BankAccount(iban, bic);
		LOGGER.info("Received request to add bank account: {}", account);
		service.addAccount(account);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void editAccount(@RequestParam("id") int id, @RequestParam("type") String type,
			@RequestParam("value") String value) {
		LOGGER.info("Received request to edit field `{}` with new value `{}` for account with id: {}", type, value, id);
		service.editAccount(id, type, value);
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		LOGGER.error("An exception occured: {}", ex.getMessage());
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("exception", ex);
		return mav;
	}

}
