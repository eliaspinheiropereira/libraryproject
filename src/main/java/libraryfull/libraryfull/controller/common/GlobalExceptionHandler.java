package libraryfull.libraryfull.controller.common;

import libraryfull.libraryfull.dto.ErroCampoDTO;
import libraryfull.libraryfull.dto.ErroRespostaDTO;
import libraryfull.libraryfull.exceptions.OperacaoNaoPermitidaException;
import libraryfull.libraryfull.exceptions.RegistroDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroRespostaDTO handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampoDTO> listaErros = fieldErrors
                .stream()
                .map(fe -> new ErroCampoDTO(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return new ErroRespostaDTO(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "erro de validação",
                listaErros
        );
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroRespostaDTO handlerRegistroDuplicadoException(RegistroDuplicadoException e) {
        return ErroRespostaDTO.conflito(e.getMessage());
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroRespostaDTO handlerOperacaoNaoPermitidaException(OperacaoNaoPermitidaException e) {
        return ErroRespostaDTO.respostaPadrão(e.getMessage());
    }
}
