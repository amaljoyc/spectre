package com.amaljoyc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.amaljoyc.model.BankAccount;
import com.amaljoyc.service.BankAccountService;

@RunWith(MockitoJUnitRunner.class)
public class SpectreControllerUnitTest {

	@Mock
	private BankAccountService service;

	@Mock
	List<BankAccount> accountList;

	@InjectMocks
	private SpectreController controller;

	private static final int PK = 123;

	private static final String BIC = "bic";
	private static final String SAMPLE_BIC = "sample_bic";
	private static final String SAMPLE_IBAN = "sample_iban";
	private static final String SAMPLE_EXCEPTION_MSG = "sample_exception_msg";

	@Test
	public void testIndex() {
		assertEquals("index", controller.index());
	}

	@Test
	public void testListAccounts() {
		when(service.listAllAccounts()).thenReturn(accountList);
		List<BankAccount> actualList = controller.listAccounts();
		assertEquals(accountList, actualList);
		verify(service, times(1)).listAllAccounts();
	}

	@Test
	public void testDeleteAccount() {
		controller.deleteAccount(PK);
		verify(service, times(1)).deleteAccount(PK);
	}

	@Test
	public void testAddAccount() {
		controller.addAccount(SAMPLE_IBAN, SAMPLE_BIC);
		verify(service, times(1)).addAccount(any(BankAccount.class));
	}

	@Test
	public void testEditAccount() {
		controller.editAccount(PK, BIC, SAMPLE_BIC);
		verify(service, times(1)).editAccount(PK, BIC, SAMPLE_BIC);
	}

	@Test
	public void testHandleException() {
		ModelAndView mav = controller.handleException(new Exception(SAMPLE_EXCEPTION_MSG));
		assertEquals("error", mav.getViewName());
		Exception ex = (Exception) mav.getModelMap().get("exception");
		assertEquals(ex.getMessage(), SAMPLE_EXCEPTION_MSG);
	}

}
