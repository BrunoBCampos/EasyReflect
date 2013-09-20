package br.com.bruno.tgi.core;

import java.lang.annotation.Annotation;

public interface AnnotationReflect {

	public <T extends Annotation> T obterAnotacao(Class<T> clazz);

	public Annotation[] obterAnotacoes();

}
