package com.identitas.exception;

import com.identitas.BaseResponse;

import javax.ws.rs.core.Response;
import java.time.Instant;

/**
 * Created by toshikijahja on 3/26/19.
 */
public class ExceptionResponseBuilder {
    public static Response buildResponse(final ResponseError responseError) {
        return Response.status(responseError.getHttpResponseCode())
                .entity(buildEntity(responseError))
                .build();
    }

    private static BaseResponse buildEntity(final ResponseError responseError) {
        return new BaseResponse()
                .setError(buildError(responseError))
                .setCurrentTime(Instant.now().toEpochMilli());
    }

    private static IdentitasError buildError(final ResponseError responseError) {
        return new IdentitasError()
                .setCode(responseError.getHttpResponseCode())
                .setErrorKey(responseError.getErrorKey())
                .setDescription(responseError.getErrorDescription());
    }
}
