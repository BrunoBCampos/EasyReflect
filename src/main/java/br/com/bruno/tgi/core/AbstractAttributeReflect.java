package br.com.bruno.tgi.core;

public interface AbstractAttributeReflect extends AttributeAnnotationReflect {

	public Object obterValor();

	public void alterarValor(Object value);
}
