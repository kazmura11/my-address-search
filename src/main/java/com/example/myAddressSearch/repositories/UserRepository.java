package com.example.myAddressSearch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myAddressSearch.models.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * ユーザー認証用
	 *
	 * @param emailAddress
	 * @return
	 */
	public User findByEmailAddressEqualsAndDeletedIsFalse(String emailAddress);

	/**
	 * ユーザー認証用
	 * 削除フラグ無視(登録時に使用)
	 *
	 * @param emailAddress
	 * @return
	 */
	public User findByEmailAddressEquals(String emailAddress);

}
