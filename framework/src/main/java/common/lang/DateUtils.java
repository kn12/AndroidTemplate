/**
 * 
 */
package common.lang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final SimpleDateFormat SDF_HH_MM_SS_SSS = new SimpleDateFormat(
			"HH:mm:ss:SSS");
	private static final SimpleDateFormat SDF_HH_MM_SS = new SimpleDateFormat(
			"HH:mm:ss");
	private static final SimpleDateFormat SDF_MMDD = new SimpleDateFormat(
			"MMdd");
	private static final SimpleDateFormat SDF_YYYYMM = new SimpleDateFormat(
			"yyyyMM");
	private static final SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat(
			"yyyyMMdd");
	private static final SimpleDateFormat SDF_YYYYMMDDHHMMSS = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	/**
	 * 信息列表返回的时间格式
	 */
	private static final SimpleDateFormat SDF_YYYYMMDD_HH_MM_SS = new SimpleDateFormat(
			"yyyyMMdd HH:mm:ss");
	private static final SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS_SSS = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss:SSS");
	private static final SimpleDateFormat SDF_HHMM = new SimpleDateFormat(
			"HHmm");

	public static String format_HHMM(Date date) {
		return format(SDF_HHMM, date);
	}

	public static String format_YYYYMMDDHHMMSS(Date date) {
		return format(SDF_YYYYMMDDHHMMSS, date);
	}

	public static String format_YYYYMMDD(Date date) {
		return format(SDF_YYYYMMDD, date);
	}

	public static String format_YYYYMM(Date date) {
		return format(SDF_YYYYMM, date);
	}

	public static String format_MMDD(Date date) {
		return format(SDF_MMDD, date);
	}

	public static String format_HH_MM_SS_SSS(Date date) {
		return format(SDF_HH_MM_SS_SSS, date);
	}

	public static String format_HH_MM_SS(Date date) {
		return format(SDF_HH_MM_SS, date);
	}

	public static String format_YYYY_MM_DD(Date date) {
		return format(SDF_YYYY_MM_DD, date);
	}

	public static String format_YYYYMMDD_HH_MM_SS(Date date) {
		return format(SDF_YYYYMMDD_HH_MM_SS, date);
	}

	public static String fromat_YYYY_MM_DD_HH_MM_SS_SSS(Date date) {
		return format(SDF_YYYY_MM_DD_HH_MM_SS_SSS, date);
	}

	/**
	 * 格式化日期
	 * 
	 * @param sdf
	 * @param date
	 * @return
	 */
	private static String format(SimpleDateFormat sdf, Date date) {
		if (date == null) {
			return null;
		} else {
			return sdf.format(date);
		}
	}

	public static Date parse_YYYYMMDD_HH_MM_SS(String source) {
		return parse(SDF_YYYYMMDD_HH_MM_SS, source);
	}

	public static Date parse_YYYYMMDD(String source) {
		return parse(SDF_YYYYMMDD, source);
	}

	public static Date parse_HHMM(String source) {
		return parse(SDF_HHMM, source);
	}

	public static Date parse_HHMMSS(String s) {
		return parse(SDF_HH_MM_SS, s);
	}

	/**
	 * 解析日期
	 * 
	 * @param sdf
	 * @param source
	 * @return
	 */
	private static Date parse(SimpleDateFormat sdf, String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		} else {
			try {
				return sdf.parse(source);
			} catch (ParseException e) {
				return null;
			}
		}
	}

}
