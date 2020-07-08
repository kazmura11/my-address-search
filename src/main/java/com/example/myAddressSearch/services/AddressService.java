package com.example.myAddressSearch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.myAddressSearch.models.entities.Address;
import com.example.myAddressSearch.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	private Specification<Address> governmentCodeContains(String governmentCode) {
		return StringUtils.isEmpty(governmentCode) ? null : (root, query, cb) -> {
			return cb.like(root.get("governmentCode"), "%" + governmentCode + "%");
		};
	}
	
	private Specification<Address> oldZipCodeContains(String oldZipCode) {
		return StringUtils.isEmpty(oldZipCode) ? null : (root, query, cb) -> {
			return cb.like(root.get("oldZipCode"), "%" + oldZipCode + "%");
		};
	}
	
	private Specification<Address> zipCodeContains(String zipCode) {
		return StringUtils.isEmpty(zipCode) ? null : (root, query, cb) -> {
			return cb.like(root.get("zipCode"), "%" + zipCode + "%");
		};
	}
	
	private Specification<Address> kanaPrefectureContains(String kanaPrefecture) {
		return StringUtils.isEmpty(kanaPrefecture) ? null : (root, query, cb) -> {
			return cb.like(root.get("kanaPrefecture"), "%" + kanaPrefecture + "%");
		};
	}
	
	private Specification<Address> kanaCityContains(String kanaCity) {
		return StringUtils.isEmpty(kanaCity) ? null : (root, query, cb) -> {
			return cb.like(root.get("kanaCity"), "%" + kanaCity + "%");
		};
	}
	
	private Specification<Address> kanaDistrictContains(String kanaDistrict) {
		return StringUtils.isEmpty(kanaDistrict) ? null : (root, query, cb) -> {
			return cb.like(root.get("kanaDistrict"), "%" + kanaDistrict + "%");
		};
	}
	
	private Specification<Address> prefectureContains(String prefecture) {
		return StringUtils.isEmpty(prefecture) ? null : (root, query, cb) -> {
			return cb.like(root.get("prefecture"), "%" + prefecture + "%");
		};
	}
	
	private Specification<Address> cityContains(String city) {
		return StringUtils.isEmpty(city) ? null : (root, query, cb) -> {
			return cb.like(root.get("city"), "%" + city + "%");
		};
	}
	
	private Specification<Address> districtContains(String district) {
		return StringUtils.isEmpty(district) ? null : (root, query, cb) -> {
			return cb.like(root.get("district"), "%" + district + "%");
		};
	}
	
	private Specification<Address> multipleDistrictFlagEquals(Boolean multipleDistrictFlag) {
		return multipleDistrictFlag == null ? null : (root, query, cb) -> {
			return cb.equal(root.get("multipleDistrictFlag"), multipleDistrictFlag);
		};
	}
	
	private Specification<Address> specialKoazaFlagEquals(Boolean specialKoazaFlag) {
		return specialKoazaFlag == null ? null : (root, query, cb) -> {
			return cb.equal(root.get("specialKoazaFlag"), specialKoazaFlag);
		};
	}
	
	private Specification<Address> chomeFlagEquals(Boolean chomeFlag) {
		return chomeFlag == null ? null : (root, query, cb) -> {
			return cb.equal(root.get("chomeFlag"), chomeFlag);
		};
	}
	
	private Specification<Address> updatingCodeEquals(Integer updatingCode) {
		return updatingCode == null ? null : (root, query, cb) -> {
			return cb.equal(root.get("updatingCode"), updatingCode);
		};
	}
	
	private Specification<Address> modifiedReasonEquals(Integer modifiedReason) {
		return modifiedReason == null ? null : (root, query, cb) -> {
			return cb.equal(root.get("modifiedReason"), modifiedReason);
		};
	}
	
	private Specification<Address> deletedEquals(boolean deleted) {
		return (root, query, cb) -> {
			return cb.equal(root.get("deleted"), deleted);
		};
	}
	
	public Page<Address> findByDeletedIsFalse(
			Pageable pageable,
			String governmentCode,
			String oldZipCode,
			String zipCode,
			String kanaPrefecture,
			String kanaCity,
			String kanaDistrict,
			String prefecture,
			String city,
			String district,
			Boolean multipleDistrictFlag,
			Boolean specialKoazaFlag,
			Boolean chomeFlag,
			Integer updatingCode,
			Integer modifiedReason) {
		return addressRepository.findAll(
				Specification
					.where(governmentCodeContains(governmentCode))
					.and(oldZipCodeContains(oldZipCode))
					.and(zipCodeContains(zipCode))
					.and(kanaPrefectureContains(kanaPrefecture))
					.and(kanaCityContains(kanaCity))
					.and(kanaDistrictContains(kanaDistrict))
					.and(prefectureContains(prefecture))
					.and(cityContains(city))
					.and(districtContains(district))
					.and(multipleDistrictFlagEquals(multipleDistrictFlag))
					.and(specialKoazaFlagEquals(specialKoazaFlag))
					.and(chomeFlagEquals(chomeFlag))
					.and(updatingCodeEquals(updatingCode))
					.and(modifiedReasonEquals(modifiedReason))
					.and(deletedEquals(false)),
					pageable
				);
	}
	
	public long getCountByDeletedIsFalse(
			String governmentCode,
			String oldZipCode,
			String zipCode,
			String kanaPrefecture,
			String kanaCity,
			String kanaDistrict,
			String prefecture,
			String city,
			String district,
			Boolean multipleDistrictFlag,
			Boolean specialKoazaFlag,
			Boolean chomeFlag,
			Integer updatingCode,
			Integer modifiedReason) {
		return addressRepository.count(
				Specification
					.where(governmentCodeContains(governmentCode))
					.and(oldZipCodeContains(oldZipCode))
					.and(zipCodeContains(zipCode))
					.and(kanaPrefectureContains(kanaPrefecture))
					.and(kanaCityContains(kanaCity))
					.and(kanaDistrictContains(kanaDistrict))
					.and(prefectureContains(prefecture))
					.and(cityContains(city))
					.and(districtContains(district))
					.and(multipleDistrictFlagEquals(multipleDistrictFlag))
					.and(specialKoazaFlagEquals(specialKoazaFlag))
					.and(chomeFlagEquals(chomeFlag))
					.and(updatingCodeEquals(updatingCode))
					.and(modifiedReasonEquals(modifiedReason))
					.and(deletedEquals(false))
				);
	}
}
