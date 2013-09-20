package br.com.bruno.tgi.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import br.com.bruno.tgi.core.AbstractAttributeReflect;
import br.com.bruno.tgi.core.AbstractMethodReflect;
import br.com.bruno.tgi.core.ClassReflect;
import br.com.bruno.tgi.core.ObjectReflect;
import br.com.bruno.tgi.exception.EasyReflectException;
import br.com.bruno.tgi.util.EasyReflectUtil;

public final class EasyReflect {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EasyReflect.class);

	public ObjectReflect noObjeto(Object object) {
		if (logger.isDebugEnabled()) {
			logger.debug("noObjeto(Object) - start"); //$NON-NLS-1$
		}

		if (!EasyReflectUtil.isNull(object)) {
			ObjectReflect returnObjectReflect = new ObjectReflectImpl(object);
			if (logger.isDebugEnabled()) {
				logger.debug("noObjeto(Object) - end"); //$NON-NLS-1$
			}
			return returnObjectReflect;
		}
		throw new EasyReflectException("O argumento recebido é nulo.");

	}

	public AbstractMethodReflect noMetodo(Method method, Object target) {

		if (!EasyReflectUtil.isNull(method)) {
			return new MethodReflectImpl(method, target);
		}
		throw new EasyReflectException("O argumento recebido é nulo.");
	}

	public AbstractAttributeReflect noAtributo(Field field, Object target) {

		if (!EasyReflectUtil.isNull(field)) {
			return new AttributeReflectImpl(field, target);
		}
		throw new EasyReflectException("O argumento recebido é nulo.");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> ClassReflect naClasse(Class<T> clazz) {

		if (!EasyReflectUtil.isNull(clazz)) {
			return new ClassReflectImpl(clazz);
		}
		throw new EasyReflectException("O argumento recebido é nulo.");
	}

	@SuppressWarnings("rawtypes")
	public ClassReflect naClasse(String className) {

		if (!EasyReflectUtil.isNull(className) || !className.trim().equals("")) {
			return new ClassReflectImpl(className);
		}
		throw new EasyReflectException("O argumento recebido é nulo.");
	}

}
