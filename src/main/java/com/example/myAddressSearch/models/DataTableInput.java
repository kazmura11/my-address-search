package com.example.myAddressSearch.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTableInput {
	private Integer length;
	private Integer start;
	// このパラメーターがないと、Showing 0 to 0 of 0 entries (filtered from NaN total entries)となってしまう。
	private Integer draw;
	@JsonProperty("orders")
	private List<Order> orders = new ArrayList<>();
	public Integer getPage() {
		return this.start / this.length; // 0, 1, 2, ...
	}
	public Integer getSize() {
		return this.length;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Order {
		private String direction;
		private String property;
		public Direction getEnumDirection() {
			return Direction.fromString(this.direction);
		}
	}
}
