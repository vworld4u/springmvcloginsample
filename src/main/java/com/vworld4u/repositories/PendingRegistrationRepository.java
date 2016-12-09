package com.vworld4u.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vworld4u.models.PendingRegistration;

public interface PendingRegistrationRepository extends CrudRepository<PendingRegistration, Long> {
	public PendingRegistration findByEmail(String email);
}
