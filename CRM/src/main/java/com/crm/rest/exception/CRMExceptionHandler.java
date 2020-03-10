package com.crm.rest.exception;

import java.time.LocalDateTime;

import com.crm.service.exceptions.NegocioException;
import com.crm.service.exceptions.RoleNaoCadastradaException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CRMExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RoleNaoCadastradaException.class)
    public ResponseEntity<?> handlerRoleNaoCadastrada(RoleNaoCadastradaException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = e.getMessage();
        Problem problem = createBuilderProblem(status, problemType, detail)
                                    .addUserMessage("Registro não foi encontrado no sistema")
                                    .build();
        return handleExceptionInternal(e,problem,new HttpHeaders(),status,request);
    }
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handlerNegocioException(NegocioException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = e.getMessage();
        Problem problem = createBuilderProblem(status, problemType, detail).addUserMessage(detail).build();
        return handleExceptionInternal(e,problem,new HttpHeaders(),status,request);
    }
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, 
                Object body, HttpHeaders headers, HttpStatus status, WebRequest request){
        if(body == null){
            body = Problem.builder()
                .addTimestamp(LocalDateTime.now())
                .addStatus(status.value())
                .addTitle(status.getReasonPhrase())
                .addUserMessage("Ocorreu um erro inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o suporte")
                .build();
        }else if(body instanceof String){
            body = Problem.builder()
                .addTimestamp(LocalDateTime.now())
                .addStatus(status.value())
                .addTitle(status.getReasonPhrase())
                .addUserMessage("Ocorreu um erro inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o suporte")
                .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.Builder createBuilderProblem(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder()
                    .addTimestamp(LocalDateTime.now())
                    .addStatus(status.value())
                    .addType(problemType.getUri())
                    .addTitle(problemType.getTitle())
                    .addDetail(detail);
    }
}