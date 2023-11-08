package br.com.bgc.controleobras.domain.excetions;

public class ExcecaoDeNegocio extends RuntimeException{

    public ExcecaoDeNegocio(String message, Object... params) {
        super(String.format(message, params));
    }
}
