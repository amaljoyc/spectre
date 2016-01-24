package com.amaljoyc.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaljoyc.dao.BankAccountDao;
import com.amaljoyc.model.BankAccount;

@Service("bankAccountService")
public class SpectreBankAccountService implements BankAccountService {

	private final static Logger LOGGER = LoggerFactory.getLogger(SpectreBankAccountService.class);

	@Autowired
	private BankAccountDao accountDao;

	@PostConstruct
	public void initDefaultAccounts() {
		LOGGER.info("Adding couple of default bank accounts as given below");
		BankAccount account1 = new BankAccount("RSA13704BE4405SA01QW00", "ABCD562D");
		addAccount(account1);
		LOGGER.info(account1.toString());
		BankAccount account2 = new BankAccount("DES01374BE5599EN01Q7X0", "XYZ0562D");
		addAccount(account2);
		LOGGER.info(account2.toString());
	}

	public BankAccount findById(int id) {
		return accountDao.findById(id);
	}

	public void addAccount(BankAccount account) {
		accountDao.addAccount(account);
		LOGGER.info("Successfully added a new bank account: {}", account);
	}

	public List<BankAccount> listAllAccounts() {
		return accountDao.listAllAccounts();
	}

	public void deleteAccount(int id) {
		accountDao.deleteAccount(id);
		LOGGER.info("Successfully deleted the bank account with id: {}", id);
	}

	public void editAccount(int id, String type, String value) {
		accountDao.editAccount(id, type, value);
		LOGGER.info("Successfully edited the field `{}` for the bank account with id: {}", type, id);
	}

}