package com.example.myAddressSearch.models.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Version;

import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer addressId;
	
	/**
	 * 全国地方公共団体コード
	 */
	@Column(name = "government_code", length = 5, nullable = false)
	private String governmentCode;
	
	/**
	 * （旧）郵便番号（5桁）
	 */
	@Column(name = "old_zip_code", length = 5, nullable = false)
	private String oldZipCode;

	/**
	 * 郵便番号（7桁）
	 */
	@Column(name = "zip_code", length = 7, nullable = false)
	private String zipCode;
	
	/**
	 * 都道府県名(半角カナ)
	 */
	@Column(name = "kana_prefecture", length = 60, nullable = false)
	private String kanaPrefecture;

	/**
	 * 市区町村名(半角カナ)
	 */
	@Column(name = "kana_city", length = 60, nullable = false)
	private String kanaCity;
	
	/**
	 * 町域名(半角カナ)
	 */
	@Column(name = "kana_district", length = 255, nullable = false)
	private String kanaDistrict;
	
	/**
	 * 都道府県名(漢字)
	 */
	@Column(name = "prefecture", length = 60, nullable = false)
	private String prefecture;

	/**
	 * 市区町村名(漢字)
	 */
	@Column(name = "city", length = 60, nullable = false)
	private String city;
	
	/**
	 * 町域名(漢字)
	 */
	@Column(name = "district", length = 255, nullable = false)
	private String district;
	
	/**
	 * 複数町域フラグ
	 * 一町域が二以上の郵便番号で表される場合の表示(「1」は該当、「0」は該当せず)
	 */
	@Column(name = "multiple_district_flag", nullable = false)
	private boolean multipleDistrictFlag;

	/**
	 * 特別小字フラグ
	 * 小字毎に番地が起番されている町域の表示(「1」は該当、「0」は該当せず)
	 */
	@Column(name = "special_koaza_flag", nullable = false)
	private boolean specialKoazaFlag;
	
	/**
	 * 町域丁目フラグ
	 * 丁目を有する町域の場合の表示(「1」は該当、「0」は該当せず)
	 */
	@Column(name = "chome_flag", nullable = false)
	private boolean chomeFlag;
	
	/**
	 * 更新の表示(「0」は変更なし、「1」は変更あり、「2」廃止（廃止データのみ使用）)
	 */
	@Column(name = "updating_code", nullable = false)
	private Integer updatingCode;
	
	/**
	 * 変更理由(「0」は変更なし、「1」市政・区政・町政・分区・政令指定都市施行、「2」住居表示の実施、「3」区画整理、「4」郵便区調整等、「5」訂正、「6」廃止（廃止データのみ使用）)
	 */
	@Column(name = "modified_reason", nullable = false)
	private Integer modifiedReason;

	@Column(name = "deleted", nullable = false)
	private boolean deleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Version
	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;
}
