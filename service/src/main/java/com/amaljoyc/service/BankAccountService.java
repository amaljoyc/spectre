package com.amaljoyc.service;

import java.util.List;

import com.amaljoyc.model.BankAccount;

public interface BankAccountService {

	BankAccount findById(int id);

	void addAccount(BankAccount account);

	List<BankAccount> listAllAccounts();

	void deleteAccount(int id);

	void editAccount(int id, String type, String value);

}