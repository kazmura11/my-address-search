package com.example.myAddressSearch.models;

import lombok.Data;

@Data
public class AjaxResponseBody<T> {
	private String message;
	private Long totalCount;
	private Long totalElements;
	private PagerData<T> page;
}
