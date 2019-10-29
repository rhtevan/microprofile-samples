package com.microprofile.samples.services.book;

import com.microprofile.samples.services.book.model.BookCreate;
import com.microprofile.samples.services.book.model.BookRead;
import com.microprofile.samples.services.book.model.BookUpdate;
import com.microprofile.samples.services.book.resource.ErrorPayload;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.eclipse.microprofile.openapi.annotations.enums.ParameterIn.PATH;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.NUMBER;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(
        title = "Book API",
        version = "1.0"
    ),
    components = @Components(
        parameters = {
            @Parameter(
                name = "bookId",
                description = "Id of the Book to perform the operation",
                required = true,
                example = "1",
                in = PATH,
                schema = @Schema(type = NUMBER)
            )
        },
        requestBodies = {
            @RequestBody(
                name = "bookCreate",
                description = "The Book to create",
                required = true,
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "bookCreate"))
            ),
            @RequestBody(
                name = "bookUpdate",
                description = "The Book to update",
                required = true,
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "bookUpdate"))
            )
        },
        responses = {
            @APIResponse(
                name = "unauthorized",
                responseCode = "401",
                description = "Unauthorized",
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                name = "forbidden",
                responseCode = "403",
                description = "Forbidden",
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                name = "notFound",
                responseCode = "404",
                description = "Object Not found",
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                name = "internalError",
                responseCode = "500",
                description = "Internal Server Error",
                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "error"))
            )
        },
        schemas = {
            @Schema(name = "error", implementation = ErrorPayload.class),
            @Schema(name = "book", implementation = BookRead.class),
            @Schema(name = "bookCreate", implementation = BookCreate.class),
            @Schema(name = "bookUpdate", implementation = BookUpdate.class),
        }
    )
)

public class BookApplication extends Application {
    // let the server discover the endpoints
}