package br.com.bruno.tgi.core;

import java.lang.annotation.Annotation;

public interface MethodAnnotationReflect extends AnnotationReflect {

	public <T extends Annotation> T obterAnotacao(Class<T> clazz,
			Object... objects);

}
