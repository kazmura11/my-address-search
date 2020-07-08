package com.example.myAddressSearch.forms.addresses;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 備考: Serializableについて
 * https://stackoverflow.com/questions/38457074/spring-mvc-should-my-domain-classes-implement-serializable-for-over-the-wire-t
 * Sessionに入れないのであれば付けなくてよさそう
 */
@Data
public class IndexForm {
	/**
	 * 全国地方公共団体コード
	 */
	@Pattern(regexp = "^[0-9]{0,7}$", message = "地方ｺｰﾄﾞ: 5桁以内の半角数字を入力してください")
	private String governmentCode;
	
	/**
	 * （旧）郵便番号（5桁）
	 */
	@Pattern(regexp = "^[0-9]{0,7}$", message = "旧郵便番号: 5桁以内の半角数字を入力してください")
	private String oldZipCode;

	/**
	 * 郵便番号（7桁）
	 */
	@Pattern(regexp = "^[0-9]{0,7}$", message = "郵便番号: 7桁以内の半角数字を入力してください")
	private String zipCode;
	
	/**
	 * 都道府県名(半角カナ)
	 */
	@Pattern(regexp = "^[ｦ-ﾟ]{0,60}$", message = "都道府県ｶﾅ: 半角ｶﾅを入力してください")
	private String kanaPrefecture;

	/**
	 * 市区町村名(半角カナ)
	 */
	@Pattern(regexp = "^[ｦ-ﾟ]{0,60}$", message = "市区町村ｶﾅ: 半角ｶﾅを入力してください")
	private String kanaCity;
	
	/**
	 * 町域名(半角カナ)
	 */
	@Pattern(regexp = "^[ｦ-ﾟ]{0,255}$", message = "町域ｶﾅ: 半角ｶﾅを入力してください")
	private String kanaDistrict;
	
	/**
	 * 都道府県名(漢字)
	 */
	@Size(max = 60, message = "都道府県: 60文字以内で入力してください")
	private String prefecture;

	/**
	 * 市区町村名(漢字)
	 */
	@Size(max = 60, message = "市区町村: 60文字以内で入力してください")
	private String city;
	
	/**
	 * 町域名(漢字)
	 */
	@Size(max = 255, message = "町域: 255文字以内で入力してください")
	private String district;
	
	/**
	 * 複数町域フラグ
	 * 一町域が二以上の郵便番号で表される場合の表示(「1」は該当、「0」は該当せず)
	 */
	private Boolean multipleDistrictFlag;

	/**
	 * 特別小字フラグ
	 * 小字毎に番地が起番されている町域の表示(「1」は該当、「0」は該当せず)
	 */
	private Boolean specialKoazaFlag;
	
	/**
	 * 町域丁目フラグ
	 * 丁目を有する町域の場合の表示(「1」は該当、「0」は該当せず)
	 */
	private Boolean chomeFlag;
	
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
}
