package com.amaljoyc.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.amaljoyc.dao.BankAccountDao;
import com.amaljoyc.model.BankAccount;

@RunWith(MockitoJUnitRunner.class)
public class SpectreBankAccountServiceUnitTest {

	@Mock
	private BankAccount account;

	@Mock
	private BankAccountDao accountDao;

	@Mock
	List<BankAccount> accountList;

	@InjectMocks
	private SpectreBankAccountService accountService;

	private static final int PK = 123;

	@Test
	public void testFindById() {
		when(accountDao.findById(PK)).thenReturn(account);
		BankAccount actualAccount = accountService.findById(PK);
		assertEquals(account, actualAccount);
		verify(accountDao, times(1)).findById(PK);
	}

	@Test
	public void testAddAccount() {
		accountService.addAccount(account);
		verify(accountDao, times(1)).addAccount(account);
	}

	@Test
	public void testListAllAccounts() {
		when(accountDao.listAllAccounts()).thenReturn(accountList);
		List<BankAccount> actualList = accountService.listAllAccounts();
		assertEquals(accountList, actualList);
		verify(accountDao, times(1)).listAllAccounts();
	}

	@Test
	public void testDeleteAccount() {
		accountService.deleteAccount(PK);
		verify(accountDao, times(1)).deleteAccount(PK);
	}

	@Test
	public void testIbanInEditAccount() {
		String iban = "sample_iban";
		when(accountDao.findById(PK)).thenReturn(account);
		accountService.editAccount(PK, "iban", iban);
		verify(accountDao, times(1)).findById(PK);
		verify(account, times(1)).setIban(iban);
		verify(account, times(0)).setBic(iban);
	}

	@Test
	public void testBicInEditAccount() {
		String bic = "sample_bic";
		when(accountDao.findById(PK)).thenReturn(account);
		accountService.editAccount(PK, "bic", bic);
		verify(accountDao, times(1)).findById(PK);
		verify(account, times(1)).setBic(bic);
		verify(account, times(0)).setIban(bic);
	}

}
