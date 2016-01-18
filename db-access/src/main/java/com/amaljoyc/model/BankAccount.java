package com.amaljoyc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "iban", nullable = false)
	private String iban;

	@Column(name = "bic", nullable = false)
	private String bic;

	public BankAccount() {
		super();
	}

	public BankAccount(String iban, String bic) {
		super();
		this.iban = iban;
		this.bic = bic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", iban=" + iban + ", bic=" + bic + "]";
	}

}
