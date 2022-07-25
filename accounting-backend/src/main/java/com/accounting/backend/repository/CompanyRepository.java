package com.accounting.backend.repository;

import com.accounting.backend.model.company.Company;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    /**
     * Save a <code>Company</code> to the data store, either inserting or updating it.
     *
     * @param company the <code>Company</code> to save
     * @see Company#isNew
     */
    Company save(Company company) throws DataAccessException;

    /**
     * Retrieve <code>Company</code>s from the data store, returning all owners
     *
     * @return a <code>Collection</code> of <code>Company</code>s (or an empty <code>Collection</code> if none
     * found)
     */
    List<Company> findAll() throws DataAccessException;

    /**
     * Delete an <code>Company</code> to the data store by <code>Company</code>.
     *
     * @param company the <code>Company</code> to delete
     *
     */
    void delete(Company company) throws DataAccessException;
}