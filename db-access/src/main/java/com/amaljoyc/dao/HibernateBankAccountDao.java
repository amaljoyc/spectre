package com.amaljoyc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amaljoyc.model.BankAccount;

@Repository("bankAccountDao")
@Transactional
public class HibernateBankAccountDao extends AbstractDao<Integer, BankAccount> implements BankAccountDao {

	public BankAccount findById(int id) {
		return getByKey(id);
	}

	public void addAccount(BankAccount account) {
		persist(account);
	}

	@SuppressWarnings("unchecked")
	public List<BankAccount> listAllAccounts() {
		Criteria criteria = createEntityCriteria();
		return (List<BankAccount>) criteria.list();
	}

	public void deleteAccount(int id) {
		Query query = getSession().createSQLQuery("delete from bank_account where id = :id");
		query.setString("id", Integer.toString(id));
		query.executeUpdate();
	}

	public void editAccount(int id, String type, String value) {
		BankAccount account = findById(id);
		if (account != null) {
			if (type.equals("iban")) {
				account.setIban(value);
			} else if (type.equals("bic")) {
				account.setBic(value);
			}
		}
	}

}