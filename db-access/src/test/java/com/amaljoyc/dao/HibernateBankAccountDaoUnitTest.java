package com.amaljoyc.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.amaljoyc.model.BankAccount;

@RunWith(MockitoJUnitRunner.class)
public class HibernateBankAccountDaoUnitTest {

	@Mock
	private SessionFactory sessionFactory;

	@Mock
	private Session session;

	@Mock
	private Criteria criteria;

	@Mock
	private SQLQuery query;

	@Mock
	private BankAccount account;

	@Mock
	List<BankAccount> accountList;

	@InjectMocks
	private HibernateBankAccountDao accountDao;

	private static final int PK = 123;

	@Test
	public void testFindById() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.get(any(Class.class), any(Integer.class))).thenReturn(account);
		BankAccount actualAccount = accountDao.findById(PK);
		assertEquals(account, actualAccount);
		verify(sessionFactory, times(1)).getCurrentSession();
	}

	@Test
	public void testAddAccount() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		accountDao.addAccount(account);
		verify(sessionFactory, times(1)).getCurrentSession();
		verify(session, times(1)).persist(account);
		;
	}

	@Test
	public void testListAllAccounts() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createCriteria(any(Class.class))).thenReturn(criteria);
		when(criteria.list()).thenReturn(accountList);
		List<BankAccount> actualList = accountDao.listAllAccounts();
		assertEquals(accountList, actualList);
		verify(sessionFactory, times(1)).getCurrentSession();
	}

	@Test
	public void testDeleteAccount() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createSQLQuery(any(String.class))).thenReturn(query);
		accountDao.deleteAccount(PK);
		verify(sessionFactory, times(1)).getCurrentSession();
		verify(query, times(1)).executeUpdate();
	}

	@Test
	public void testIbanInEditAccount() {
		String iban = "sample_iban";
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.get(any(Class.class), any(Integer.class))).thenReturn(account);
		accountDao.editAccount(PK, "iban", iban);
		verify(account, times(1)).setIban(iban);
		verify(account, times(0)).setBic(iban);
	}

	@Test
	public void testBicInEditAccount() {
		String bic = "sample_bic";
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.get(any(Class.class), any(Integer.class))).thenReturn(account);
		accountDao.editAccount(PK, "bic", bic);
		verify(account, times(1)).setBic(bic);
		verify(account, times(0)).setIban(bic);
	}

}
