package br.com.bruno.tgi.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import br.com.bruno.tgi.core.AbstractAttributeReflect;
import br.com.bruno.tgi.exception.EasyReflectException;

public class AttributeReflectImpl implements AbstractAttributeReflect {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AttributeReflectImpl.class);

	private Field field;

	private Object object;

	private String attName;

	public AttributeReflectImpl(Field field) {
		this.field = field;
	}

	public AttributeReflectImpl(Object object, String attName) {
		this.object = object;
		this.attName = attName;
	}

	public AttributeReflectImpl(Object object, Field field) {
		this.object = object;
		this.field = field;
	}

	public AttributeReflectImpl(Object object) {
		this.object = object;
	}

	public AttributeReflectImpl(Field field, Object target) {
		this.field = field;
		this.object = target;
	}

	@Override
	public Object obterValor() {
		if (logger.isDebugEnabled()) {
			logger.debug("obterValor() - start"); //$NON-NLS-1$
		}

		if (field == null) {
			field = locateField();
		}

		field.setAccessible(true);

		try {
			Object returnObject = field.get(object);
			if (logger.isDebugEnabled()) {
				logger.debug("obterValor() - end"); //$NON-NLS-1$
			}
			return returnObject;
		} catch (IllegalArgumentException e) {
			logger.error("obterValor()", e); //$NON-NLS-1$

			throw new EasyReflectException(e);
		} catch (IllegalAccessException e) {
			logger.error("obterValor()", e); //$NON-NLS-1$
			throw new EasyReflectException(e);
		}
	}

	@Override
	public void alterarValor(Object value) {
		if (logger.isDebugEnabled()) {
			logger.debug("alterarValor(Object) - start"); //$NON-NLS-1$
		}

		if (field == null) {
			field = locateField();
		}

		field.setAccessible(true);

		try {
			field.set(object, value);
		} catch (IllegalArgumentException e) {
			logger.error("alterarValor(Object)", e); //$NON-NLS-1$

			throw new EasyReflectException(e);
		} catch (IllegalAccessException e) {
			logger.error("alterarValor(Object)", e); //$NON-NLS-1$
			throw new EasyReflectException(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("alterarValor(Object) - end"); //$NON-NLS-1$
		}
	}

	private Field locateField() {
		if (logger.isDebugEnabled()) {
			logger.debug("locateField() - start"); //$NON-NLS-1$
		}

		Class<?> clazz = object.getClass();

		while (clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals(attName)) {
					if (logger.isDebugEnabled()) {
						logger.debug("locateField() - end"); //$NON-NLS-1$
					}
					return field;
				}
			}
			// Se não encontrou o atributo procurado, procura na superclasse
			// direta.
			clazz = clazz.getSuperclass();
		}
		throw new EasyReflectException("Atributo não encontrado.");
	}

	@Override
	public <T extends Annotation> T obterAnotacao(Class<T> clazz) {
		if (logger.isDebugEnabled()) {
			logger.debug("obterAnotacao(Class<T>) - start"); //$NON-NLS-1$
		}

		if (field == null) {
			field = locateField();
		}
		T returnT = field.getAnnotation(clazz);
		if (logger.isDebugEnabled()) {
			logger.debug("obterAnotacao(Class<T>) - end"); //$NON-NLS-1$
		}
		return returnT;
	}

	@Override
	public Annotation[] obterAnotacoes() {
		if (logger.isDebugEnabled()) {
			logger.debug("obterAnotacoes() - start"); //$NON-NLS-1$
		}

		if (field == null) {
			field = locateField();
		}
		Annotation[] returnAnnotationArray = field.getAnnotations();
		if (logger.isDebugEnabled()) {
			logger.debug("obterAnotacoes() - end"); //$NON-NLS-1$
		}
		return returnAnnotationArray;
	}

}
