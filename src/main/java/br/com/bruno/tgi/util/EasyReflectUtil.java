package br.com.bruno.tgi.util;

public class EasyReflectUtil {

	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static boolean isNull(String value) {

		if (value == null || value.trim().equals("")) {
			throw new IllegalArgumentException("O parametro é nulo.");
		}
		return false;
	}

}
