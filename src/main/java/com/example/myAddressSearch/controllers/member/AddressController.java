package com.example.myAddressSearch.controllers.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myAddressSearch.forms.addresses.IndexForm;
import com.example.myAddressSearch.models.PagerData;
import com.example.myAddressSearch.models.entities.Address;
import com.example.myAddressSearch.services.AddressService;
import com.example.myAddressSearch.util.UriUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/addresses")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	/**
	 * 普通の非AjaxのGet
	 * 例外はBindingResultで処理
	 * 
	 * @param indexForm
	 * @param bindingResult
	 * @param model
	 * @param pageable
	 * @return
	 */
	@GetMapping
	public String getAddresses(@Validated IndexForm indexForm, BindingResult bindingResult, Model model,
			@PageableDefault(page = 0, value = 100, sort = {"zipCode"}, direction = Direction.ASC) Pageable pageable) {
		log.debug("pageable: {}", pageable);
		Page<Address> addresses = addressService.findByDeletedIsFalse(
				pageable,
				indexForm.getGovernmentCode(),
				indexForm.getOldZipCode(),
				indexForm.getZipCode(),
				indexForm.getKanaPrefecture(),
				indexForm.getKanaCity(),
				indexForm.getKanaDistrict(),
				indexForm.getPrefecture(),
				indexForm.getCity(),
				indexForm.getDistrict(),
				indexForm.getMultipleDistrictFlag(),
				indexForm.getSpecialKoazaFlag(),
				indexForm.getChomeFlag(),
				indexForm.getUpdatingCode(),
				indexForm.getModifiedReason());
		long totalCount = addressService.getCountByDeletedIsFalse(
				indexForm.getGovernmentCode(),
				indexForm.getOldZipCode(),
				indexForm.getZipCode(),
				indexForm.getKanaPrefecture(),
				indexForm.getKanaCity(),
				indexForm.getKanaDistrict(),
				indexForm.getPrefecture(),
				indexForm.getCity(),
				indexForm.getDistrict(),
				indexForm.getMultipleDistrictFlag(),
				indexForm.getSpecialKoazaFlag(),
				indexForm.getChomeFlag(),
				indexForm.getUpdatingCode(),
				indexForm.getModifiedReason());
		if (addresses == null || CollectionUtils.isEmpty(addresses.getContent())) {
			bindingResult.reject("", "レコードが見つかりません");
			return "/member/addresses";
		}
		PagerData<Address> page = new PagerData<>(addresses, "/member/addresses?" + UriUtils.formToQueryParam(IndexForm.class, indexForm));
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("page", page);
		model.addAttribute("content", page.getContent());

		return "/member/addresses";
	}
	
	/**
	 * 単にAjaxページに飛ばすだけ
	 * @return
	 */
	@GetMapping("ajax")
	public String getAddressAjax() {
		return "/member/addresses-ajax";
	}
	
	/**
	 * 単にDataTable用ページに飛ばすだけ
	 * @return
	 */
	@GetMapping("jq-dt")
	public String getAddressJqDt() {
		return "/member/addresses-jq-dt";
	}
}
