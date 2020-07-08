package com.example.myAddressSearch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.myAddressSearch.models.entities.Address;

/**
 * 住所検索用のRepository
 * Specificationを使うためJpaSpecificationExecutorを継承しています
 */
public interface AddressRepository extends JpaRepository<Address, Integer>,
		JpaSpecificationExecutor<Address> {

}
