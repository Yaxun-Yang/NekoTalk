package org.csu.nekotalk.annotation.exception;

public class ParameterErrorException extends RuntimeException {
        public ParameterErrorException()
        {

        }
        public ParameterErrorException(String message)
        {
            super(message);
        }
}
