package br.com.bruno.tgi.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface ClassReflect extends AnnotationReflect {

	public void obterAtributo();

	public Field[] obterAtributos();

	public Method obterMetodo();

	public Method obterMetodoComParametros(Object... objects);

	public Method obterMetodos();
}
