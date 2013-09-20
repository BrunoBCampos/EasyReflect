package br.com.bruno.tgi.impl;

import org.apache.log4j.Logger;

import br.com.bruno.tgi.core.AbstractAttributeReflect;
import br.com.bruno.tgi.core.AbstractMethodReflect;
import br.com.bruno.tgi.core.ObjectReflect;
import br.com.bruno.tgi.util.EasyReflectUtil;

public class ObjectReflectImpl implements ObjectReflect {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ObjectReflectImpl.class);

	private Object object;

	public ObjectReflectImpl(Object object) {
		this.object = object;
	}

	@Override
	public AbstractMethodReflect noMetodo(String methodName) {
		if (logger.isDebugEnabled()) {
			logger.debug("noMetodo(String) - start"); //$NON-NLS-1$
		}

		if (!EasyReflectUtil.isNull(methodName)) {
			AbstractMethodReflect returnNamedMethodReflect = new MethodReflectImpl(
					object, methodName);
			if (logger.isDebugEnabled()) {
				logger.debug("noMetodo(String) - end"); //$NON-NLS-1$
			}
			return returnNamedMethodReflect;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("noMetodo(String) - end"); //$NON-NLS-1$
		}
		return null;
	}

	@Override
	public AbstractAttributeReflect noAtributo(String attName) {
		if (logger.isDebugEnabled()) {
			logger.debug("noAtributo(String) - start"); //$NON-NLS-1$
		}

		if (!EasyReflectUtil.isNull(attName)) {
			AbstractAttributeReflect returnNamedAttributeReflect = new AttributeReflectImpl(
					object, attName);
			if (logger.isDebugEnabled()) {
				logger.debug("noAtributo(String) - end"); //$NON-NLS-1$
			}
			return returnNamedAttributeReflect;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("noAtributo(String) - end"); //$NON-NLS-1$
		}
		return null;
	}

}
