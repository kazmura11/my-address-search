package com.example.myAddressSearch.util;

import java.lang.reflect.Field;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UriUtils {
	/**
	 * ReflectionによりformからQueryParamを作成する
	 * nullのものは無視する
	 * 
	 * @param clazz
	 * @param o
	 * @return
	 */
	public static <T> String formToQueryParam(Class<T> clazz, Object o) {
		String queryString = "";
		try {
			for (Field f : clazz.getDeclaredFields()) {
				f.setAccessible(true);
				Object value = f.get(o);
				if (value != null) {
					queryString += (f.getName() + "=" +  String.valueOf(value) + "&");
				}
			}
		} catch (Exception e) {
			log.error("UriIUtils#formToQueryParam() Error: " + e.getMessage());
		}
		log.info("query: " + queryString);
		return queryString.length() == 0 ? "" : queryString.substring(0, queryString.length() - 1);  // 余計な最後の&を削って返す
	}
}
