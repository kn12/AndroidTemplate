/**
 * 
 */
package common.lang;

import java.math.BigDecimal;

public class StringUtils {
	/**
	 * <p>
	 * Checks if a String is empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer trims the
	 * String. That functionality is available in isBlank().
	 * </p>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is empty or null
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	// Equals
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Compares two Strings, returning <code>true</code> if they are equal.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code>s are handled without exceptions. Two <code>null</code>
	 * references are considered to be equal. The comparison is case sensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.equals(null, null)   = true
	 * StringUtils.equals(null, "abc")  = false
	 * StringUtils.equals("abc", null)  = false
	 * StringUtils.equals("abc", "abc") = true
	 * StringUtils.equals("abc", "ABC") = false
	 * </pre>
	 * 
	 * @see java.lang.String#equals(Object)
	 * @param str1
	 *            the first String, may be null
	 * @param str2
	 *            the second String, may be null
	 * @return <code>true</code> if the Strings are equal, case sensitive, or
	 *         both <code>null</code>
	 */
	public static boolean equals(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	/**
	 * 将浮点数格式化成百分比
	 * 
	 * @param dValue
	 * @return
	 */
	public static String formatPrecent(double dValue) {
		java.text.NumberFormat percentFormat = java.text.NumberFormat
				.getPercentInstance();
		percentFormat.setMaximumFractionDigits(2); // 最大小数位数
		percentFormat.setMinimumFractionDigits(2); // 最小小数位数
		// percentFormat.setMaximumIntegerDigits(2);// 最大整数位数
		// percentFormat.setMinimumIntegerDigits(2);// 最小整数位数
		return percentFormat.format(dValue);// 自动转换成百分比显示..
	}

	/**
	 * 提供精确的加法运算。
	 */
	public static String saddString(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).toString();
	}

	/**
	 * 提供精确的加法运算。
	 */
	public static double saddDouble(String v1) {// , String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		// BigDecimal b2 = new BigDecimal(v2);
		// return b1.add(b2).doubleValue();
		return b1.doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的乘法法运算。
	 * 
	 * @param v1
	 * 
	 * @param v2
	 * 
	 * @return 两个参数的积
	 */
	public static double mul(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 数组是否相等
	 * 
	 * @param s1
	 * @param s2
	 * @return true相等 false不相等
	 */
	public static boolean isArrayEqual(String[] s1, String[] s2) {
		if (s1 == null && s2 == null)
			return true;
		else if ((s1 == null && s2 != null) || (s1 != null && s2 == null))
			return false;

		if (s1.length != s2.length)
			return false;
		else
			for (int i = 0; i < s1.length; i++) {
				if (!s1[i].equals(s2[i]))
					return false;
			}
		return true;
	}

}
