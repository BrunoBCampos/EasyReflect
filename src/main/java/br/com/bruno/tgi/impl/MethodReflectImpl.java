package br.com.bruno.tgi.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.bruno.tgi.core.AbstractMethodReflect;
import br.com.bruno.tgi.exception.EasyReflectException;

public class MethodReflectImpl implements AbstractMethodReflect {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(MethodReflectImpl.class);

	private Method method;
	private String methodName;
	private Object object;

	private static Map<Class, Class> classesPrimitivos = null;

	static {

		classesPrimitivos = new HashMap<Class, Class>();
		classesPrimitivos.put(Integer.class, int.class);
		classesPrimitivos.put(Float.class, float.class);
		classesPrimitivos.put(Double.class, double.class);
		classesPrimitivos.put(Byte.class, byte.class);
		classesPrimitivos.put(Character.class, char.class);
		classesPrimitivos.put(Float.class, float.class);
		classesPrimitivos.put(Float.class, float.class);

	}

	public MethodReflectImpl(Method method) {
		this.method = method;
		this.methodName = method.getName();
	}

	public MethodReflectImpl(Object object, String methodName) {
		this.object = object;
		this.methodName = methodName;
	}

	public MethodReflectImpl(Object object, Method method) {
		this.object = object;
		this.method = method;
		this.methodName = method.getName();
	}

	public MethodReflectImpl(Object object) {
		this.object = object;
	}

	public MethodReflectImpl(Method method, Object target) {
		this.method = method;
		this.object = target;
	}

	@Override
	public Annotation[] obterAnotacoes() {
		if (logger.isDebugEnabled()) {
			logger.debug("obterAnotacoes() - start"); //$NON-NLS-1$
		}

		if (method != null) {
			Annotation[] returnAnnotationArray = method.getAnnotations();
			if (logger.isDebugEnabled()) {
				logger.debug("obterAnotacoes() - end"); //$NON-NLS-1$
			}
			return returnAnnotationArray;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("obterAnotacoes() - end"); //$NON-NLS-1$
		}
		return null;
	}

	@Override
	public Object executarSemParametros() {
		if (logger.isDebugEnabled()) {
			logger.debug("executarSemParametros() - start"); //$NON-NLS-1$
		}

		Method m = locateMethodWhitoutParams();
		m.setAccessible(true);
		try {
			Object returnObject = m.invoke(object, null);
			if (logger.isDebugEnabled()) {
				logger.debug("executarSemParametros() - end"); //$NON-NLS-1$
			}
			return returnObject;
		} catch (IllegalAccessException e) {
			logger.error("executarSemParametros()", e); //$NON-NLS-1$
			throw new EasyReflectException(e);
		} catch (IllegalArgumentException e) {
			logger.error("executarSemParametros()", e); //$NON-NLS-1$
			throw new EasyReflectException(e);
		} catch (InvocationTargetException e) {
			logger.error("executarSemParametros()", e.getTargetException()); //$NON-NLS-1$
			throw new EasyReflectException(e.getTargetException());
		}
	}

	@Override
	public Object executarComParametros(Object... objects) {
		if (logger.isDebugEnabled()) {
			logger.debug("executarComParametros(Object) - start"); //$NON-NLS-1$
		}

		Method m = locateMethodWhitParams(objects);
		m.setAccessible(true);
		try {
			Object returnObject = m.invoke(object, objects);
			if (logger.isDebugEnabled()) {
				logger.debug("executarComParametros(Object) - end"); //$NON-NLS-1$
			}
			return returnObject;
		} catch (IllegalAccessException e) {
			logger.error("executarComParametros(Object)", e); //$NON-NLS-1$
			throw new EasyReflectException(e);
		} catch (IllegalArgumentException e) {
			logger.error("executarComParametros(Object)", e); //$NON-NLS-1$
			throw new EasyReflectException(e);
		} catch (InvocationTargetException e) {
			logger.error(
					"executarComParametros(Object)", e.getTargetException()); //$NON-NLS-1$
			throw new EasyReflectException(e.getTargetException());
		}

	}

	private Method locateMethodWhitParams(Object... params) {
		if (logger.isDebugEnabled()) {
			logger.debug("locateMethodWhitParams(Object) - start"); //$NON-NLS-1$
		}

		Class clazz = object.getClass();

		while (clazz != null) {
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					if (compareParams(method, params)) {
						if (logger.isDebugEnabled()) {
							logger.debug("locateMethodWhitParams(Object) - end"); //$NON-NLS-1$
						}
						return method;
					}
				}
			}
			// Se não encontrou o metodo procurado, procura na superclasse
			// direta.
			clazz = clazz.getSuperclass();
		}
		throw new EasyReflectException("Metodo nao encontrado.");
	}

	private boolean compareParams(Method method, Object[] userParams) {
		if (logger.isDebugEnabled()) {
			logger.debug("compareParams(Method, Object[]) - start"); //$NON-NLS-1$
		}

		Class<?> currentParam = null;

		Class<?>[] targetParams = method.getParameterTypes();

		if (targetParams.length != userParams.length) {
			if (logger.isDebugEnabled()) {
				logger.debug("compareParams(Method, Object[]) - end"); //$NON-NLS-1$
			}
			return false;
		}

		for (int i = 0; i < targetParams.length; i++) {

			currentParam = userParams[i].getClass();
			if (classesPrimitivos.containsKey(currentParam)) {
				currentParam = classesPrimitivos.get(currentParam);
			}

			if (!targetParams[i].equals(currentParam)) {
				if (logger.isDebugEnabled()) {
					logger.debug("compareParams(Method, Object[]) - end"); //$NON-NLS-1$
				}
				return false;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("compareParams(Method, Object[]) - end"); //$NON-NLS-1$
		}
		return true;
	}

	private Method locateMethodWhitoutParams() {
		if (logger.isDebugEnabled()) {
			logger.debug("locateMethodWhitoutParams() - start"); //$NON-NLS-1$
		}

		Class<?> clazz = object.getClass();

		while (clazz != null) {
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {

				if (method.getName().equals(methodName)) {
					if (logger.isDebugEnabled()) {
						logger.debug("locateMethodWhitoutParams() - end"); //$NON-NLS-1$
					}
					return method;
				}
			}
			// Se não encontrou o metodo procurado, procura na superclasse
			// direta.
			clazz = clazz.getSuperclass();
		}
		throw new EasyReflectException("Metodo não encontrado");
	}

	@Override
	public <T extends Annotation> T obterAnotacao(Class<T> clazz) {
		if (logger.isDebugEnabled()) {
			logger.debug("obterAnotacao(Class<T>) - start"); //$NON-NLS-1$
		}

		if (method == null) {
			method = locateMethodWhitoutParams();
		}

		T returnT = method.getAnnotation(clazz);
		if (logger.isDebugEnabled()) {
			logger.debug("obterAnotacao(Class<T>) - end"); //$NON-NLS-1$
		}
		return returnT;
	}

	@Override
	public <T extends Annotation> T obterAnotacao(Class<T> clazz,
			Object... objects) {
		if (logger.isDebugEnabled()) {
			logger.debug("obterAnotacao(Class<T>, Object) - start"); //$NON-NLS-1$
		}

		if (method == null) {
			method = locateMethodWhitParams(objects);
		}
		T returnT = method.getAnnotation(clazz);
		if (logger.isDebugEnabled()) {
			logger.debug("obterAnotacao(Class<T>, Object) - end"); //$NON-NLS-1$
		}
		return returnT;
	}

}
