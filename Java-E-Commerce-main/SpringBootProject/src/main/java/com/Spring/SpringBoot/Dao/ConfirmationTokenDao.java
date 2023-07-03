package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("confirmationTokenDao")
public interface ConfirmationTokenDao extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);

}
