package br.com.bruno.tgi.core;

public interface AbstractMethodReflect extends MethodAnnotationReflect {

	public Object executarSemParametros();

	public Object executarComParametros(Object... objects);

}
