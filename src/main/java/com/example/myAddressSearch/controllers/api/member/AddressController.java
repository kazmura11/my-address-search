package com.example.myAddressSearch.controllers.api.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAddressSearch.forms.api.addresses.IndexForm;
import com.example.myAddressSearch.models.AjaxResponseBody;
import com.example.myAddressSearch.models.DataTableInput;
import com.example.myAddressSearch.models.DataTableOutput;
import com.example.myAddressSearch.models.PagerData;
import com.example.myAddressSearch.models.entities.Address;
import com.example.myAddressSearch.services.AddressService;

import lombok.extern.slf4j.Slf4j;

/**
 * Vueから呼ばれるAPI
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/member/addresses")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	/**
	 * Ajax(Vue)用のGet
	 * 例外はhandlers.api.MyRestExceptionHandlerを利用
	 * @param indexForm
	 * @param errors
	 * @param pageable
	 * @return
	 * 
	 * @see https://terasolunaorg.github.io/guideline/public_review/ArchitectureInDetail/Validation.html
	 * @Validatedは、Bean Validation標準ではなく、Springの独自アノテーションである。
	 * Bean Validation標準のjavax.validation.Validアノテーションも使用できるが、
	 * @Validatedは@Validに比べて、 バリデーションのグループを指定できる点で優れているため、
	 * Controllerの引数には、@Validatedを使用することを推奨する。
	 */
	@GetMapping
	public ResponseEntity<?> getAddresses(@Validated IndexForm indexForm,
			@PageableDefault(page = 0, value = 100)
			@SortDefault.SortDefaults({
				@SortDefault(sort = "zipCode", direction = Direction.ASC)
			}) Pageable pageable) {
		log.debug("GET /api/member/addresses");
		log.debug("pageable: {}", pageable);
		
		AjaxResponseBody<Address> result = queryAddresses(indexForm, pageable);
		
		log.debug("GET /api/member/addresses END");
		return ResponseEntity.ok(result);
	}
	
	
	private AjaxResponseBody<Address> queryAddresses(IndexForm indexForm, Pageable pageable) {
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
		AjaxResponseBody<Address> result = new AjaxResponseBody<>();
		if (addresses == null || CollectionUtils.isEmpty(addresses.getContent())) {
			result.setMessage("レコードが見つかりません");
			return result;
		}
		PagerData<Address> page = new PagerData<>(addresses, "/member/addresses/ajax");
		result.setTotalElements(addresses.getTotalElements());
		result.setTotalCount(totalCount);
		result.setPage(page);
		return result;
	}
	
	/**
	 * Ajax(DataTable)用のGet
	 * 例外はhandlers.api.MyRestExceptionHandlerを利用
	 * @param indexForm
	 * @param dataTable
	 * @return
	 */
	@GetMapping("datatable")
	public ResponseEntity<?> getAddressesForDatatable(@Validated IndexForm indexForm,
			DataTableInput dataTable) {
		log.debug("GET /api/member/addresses/datatable");
		log.debug("dataTable: {}", dataTable);
		log.debug("orders: {}", dataTable.getOrders());
		
		List<Order> orders = dataTable.getOrders().stream().map(o -> new Order(o.getEnumDirection(), o.getProperty())).collect(Collectors.toList());
		Pageable pageable = PageRequest.of(dataTable.getPage(), dataTable.getSize(), Sort.by(orders));
		
		DataTableOutput<Address> result = queryAddressesForDatatable(indexForm, pageable, dataTable);
		
		log.debug("GET /api/member/addresses/datatable END");
		return ResponseEntity.ok(result);
	}
	
	private DataTableOutput<Address> queryAddressesForDatatable(IndexForm indexForm, Pageable pageable, DataTableInput dataTable) {
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
		DataTableOutput<Address> result = new DataTableOutput<>();
		if (addresses == null || CollectionUtils.isEmpty(addresses.getContent())) {
			result.setError("レコードが見つかりません");
			return result;
		}
		result.setDraw(dataTable.getDraw());
		result.setData(addresses.getContent());
		result.setRecordsTotal(totalCount);
		result.setRecordsFiltered(addresses.getTotalElements());
		return result;
	}
}
