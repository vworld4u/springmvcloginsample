package com.vworld4u.services;

import com.vworld4u.models.User;

public interface RegistrationHandler {
	public void sendRegistrationEmail(User user);
	public void verifyUser(String code, String email);
}
