package libraryfull.libraryfull.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroRespostaDTO(int status, String mensagem, List<ErroCampoDTO> erros) {

    public static ErroRespostaDTO respostaPadrão(String mensagem){
        return  new ErroRespostaDTO(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static ErroRespostaDTO conflito(String mensagem){
        return new ErroRespostaDTO(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
