/**
 * JavaScript ObjectをURIのクエリパラメータに変換する
 * @param param
 * @returns
 */
function jsObjToUriParam(param) {
	var uriParam = "";
	for (var key in param) {
		if (uriParam !== "") {
			uriParam += "&";
		}
		uriParam += key + "=" + encodeURIComponent(param[key]);
	}
	return uriParam;
}