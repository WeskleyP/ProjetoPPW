package com.crm.service.exceptions;

public class EmailClienteExistente extends RuntimeException {

	public EmailClienteExistente (String message) {
		super(message);
	}
}
