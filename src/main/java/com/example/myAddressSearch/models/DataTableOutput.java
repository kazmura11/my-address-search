package com.example.myAddressSearch.models;

import java.util.List;

import lombok.Data;

@Data
public class DataTableOutput<T> {
	private Integer draw;
	private Long recordsTotal;
	private Long recordsFiltered;
	private List<T> data;
	private String error;
}
