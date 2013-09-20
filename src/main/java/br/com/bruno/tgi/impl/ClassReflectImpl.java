package br.com.bruno.tgi.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import br.com.bruno.tgi.core.ClassReflect;
import br.com.bruno.tgi.exception.EasyReflectException;

public class ClassReflectImpl<T> implements ClassReflect {

	private Class<T> clazz = null;

	public ClassReflectImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public ClassReflectImpl(String className) {

		try {
			this.clazz = (Class<T>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new EasyReflectException(e);
		}
	}

	@Override
	public <T extends Annotation> T obterAnotacao(Class<T> clazz) {
		return null;
	}

	@Override
	public Annotation[] obterAnotacoes() {
		return null;
	}

	@Override
	public void obterAtributo() {

	}

	@Override
	public Method obterMetodo() {
		return null;
	}

	@Override
	public Method obterMetodoComParametros(Object... objects) {
		return null;
	}

	@Override
	public Field[] obterAtributos() {
		return null;
	}

	@Override
	public Method obterMetodos() {
		return null;
	}

}
