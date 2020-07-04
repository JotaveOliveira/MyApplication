package br.com.IHelp.enumerator;

import java.util.Optional;

/**
 * Enum responsável por
 * conter os tipos de documentos
 */
public enum TipoDocumento {
    TIPO_RG("RG"),
    TIPO_RNE("RNE"),
    TIPO_PASSAPORTE("PASSAPORTE"),
    TIPO_CLASSE("CLASSE");

    private String tipoDocumento;

    private TipoDocumento(String tipoDocumento){
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public Optional<String> validaNumero(String tipoDocumento, String numero){
        for(TipoDocumento tipo : values()){
            if(tipo.getTipoDocumento().equalsIgnoreCase(tipoDocumento) && numero == null){
                return Optional.empty();
            }
        }
        return Optional.of(tipoDocumento);
    }

    public Optional<String> validaDataExpiracao(String tipoDocumento, String dataExpiracao){
        for(TipoDocumento tipo : values()){
            if(tipo.getTipoDocumento().equalsIgnoreCase(tipoDocumento) && dataExpiracao == null){
                return Optional.empty();
            }
        }
        return Optional.of(tipoDocumento);
    }
}
