package com.fwk.school4.utils;

public class DataConversionUtils {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 将bytes数组转换为String
	 * 
	 * @param bytes
	 *            数组
	 * @return 如果byte数组为null或长度等于0，则返回null
	 */
	public static String toHexString(byte[] bytes) {

		if (bytes == null || bytes.length == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		String card = "";
		try {
			for (int i = 0; i < bytes.length; i++) {
				sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >>> 4]);
				sb.append(HEX_DIGITS[bytes[i] & 0x0f]);
			}
			card = sb.toString();
			if (10 > sb.toString().length()) {
				StringBuilder strCord = new StringBuilder();
				for (int i = 0; i < sb.length(); i++) {
					strCord.insert(0, sb.substring(i, i + 2));
					i++;
				}
				card = strCord.toString();
			}
		} catch (Exception ex) {
			sb.append("ex:" + ex.toString());
		}
		return card;
	}
}
