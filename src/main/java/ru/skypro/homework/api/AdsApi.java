/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.2.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ru.skypro.homework.api;

import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.FullAds;
import ru.skypro.homework.model.ResponseWrapperAds;
import ru.skypro.homework.model.ResponseWrapperComment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.dto.*;

import javax.validation.Valid;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-16T21:17:34.091476600+03:00[Europe/Moscow]")
@Validated
@Tag(name = "ads", description = "the ads API")
public interface AdsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /ads : addAds
     *
     * @param properties  (required)
     * @param image  (required)
     * @return Created (status code 201)
     *         or Not Found (status code 404)
     *         or Forbidden (status code 403)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "addAds",
        summary = "addAds",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
        }
    )
    @PostMapping(
        value = "/ads"
    )
    ResponseEntity<AdsDto> addAds(
        @Parameter(name = "properties", description = "", required = true) @Valid @RequestParam(value = "properties", required = true) CreateAdsDto properties,
        @Parameter(name = "image", description = "", required = true) @RequestPart(value = "image", required = true) MultipartFile image
    );


    /**
     * POST /ads/{ad_pk}/comments : addComments
     *
     * @param adPk  (required)
     * @param comment  (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     *         or Forbidden (status code 403)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "addComments",
        summary = "addComments",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CommentDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
        }
    )
    @PostMapping(
        value = "/ads/{ad_pk}/comments"
    )
    ResponseEntity<CommentDto> addComments(
        @Parameter(name = "ad_pk", description = "", required = true) @PathVariable("ad_pk") String adPk,
        @Parameter(name = "Comment", description = "", required = true) @Valid @RequestBody CommentDto comment
    );


    /**
     * DELETE /ads/{ad_pk}/comments/{id} : deleteComments
     *
     * @param adPk  (required)
     * @param id  (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     *         or Forbidden (status code 403)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "deleteComments",
        summary = "deleteComments",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
        }
    )
    @DeleteMapping(
        value = "/ads/{ad_pk}/comments/{id}"
    )
    ResponseEntity<Void> deleteComments(
        @Parameter(name = "ad_pk", description = "", required = true) @PathVariable("ad_pk") String adPk,
        @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    );


    /**
     * GET /ads
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getALLAds",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperAdsDto.class))
            })
        }
    )
    @GetMapping(
        value = "/ads"
    )
    ResponseEntity<ResponseWrapperAdsDto> getALLAds();

    /**
     * GET /ads/{id} : getFullAd
     *
     * @param id  (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     */
    @Operation(
        operationId = "getAds",
        summary = "getFullAd",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = FullAdsDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found")
        }
    )
    @GetMapping(
        value = "/ads/{id}"
    )
    ResponseEntity<FullAdsDto> getAds(
        @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    );


    /**
     * GET /ads/me : getAdsMe
     *
     * @param authenticated  (optional)
     * @param authorities0Authority  (optional)
     * @param credentials  (optional)
     * @param details  (optional)
     * @param principal  (optional)
     * @return OK (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     */
    @Operation(
        operationId = "getAdsMeUsingGET",
        summary = "getAdsMe",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperAdsDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
        }
    )
    @GetMapping(
        value = "/ads/me"
    )
    ResponseEntity<ResponseWrapperAdsDto> getAdsMeUsingGET(
        @Parameter(name = "authenticated", description = "") @Valid @RequestParam(value = "authenticated", required = false) Boolean authenticated,
        @Parameter(name = "authorities[0].authority", description = "") @Valid @RequestParam(value = "authorities[0].authority", required = false) String authorities0Authority,
        @Parameter(name = "credentials", description = "") @Valid @RequestParam(value = "", required = false) Object credentials,
        @Parameter(name = "details", description = "") @Valid @RequestParam(value = "", required = false) Object details,
        @Parameter(name = "principal", description = "") @Valid @RequestParam(value = "", required = false) Object principal
    );
    /**
     * GET /ads/{ad_pk}/comments : getComments
     *
     * @param adPk  (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     */
    @Operation(
        operationId = "getComments",
        summary = "getComments",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperCommentDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found")
        }
    )
    @GetMapping(
        value = "/ads/{ad_pk}/comments"
    )
    ResponseEntity<ResponseWrapperCommentDto> getComments(
        @Parameter(name = "ad_pk", description = "", required = true) @PathVariable("ad_pk") String adPk
    );


    /**
     * GET /ads/{ad_pk}/comments/{id} : getComments
     *
     * @param adPk  (required)
     * @param id  (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     */
    @Operation(
        operationId = "getComments1",
        summary = "getComments",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CommentDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found")
        }
    )
    @GetMapping(
        value = "/ads/{ad_pk}/comments/{id}"
    )
    ResponseEntity<CommentDto> getComments1(
        @Parameter(name = "ad_pk", description = "", required = true) @PathVariable("ad_pk") String adPk,
        @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    );


    /**
     * DELETE /ads/{id} : removeAds
     *
     * @param id  (required)
     * @return No Content (status code 204)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     */
    @Operation(
        operationId = "removeAds",
        summary = "removeAds",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
        }
    )
    @DeleteMapping(
        value = "/ads/{id}"
    )
    ResponseEntity<Void> removeAds(
        @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    );


    /**
     * PATCH /ads/{id} : updateAds
     *
     * @param id  (required)
     * @param createAds  (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     *         or Forbidden (status code 403)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "updateAds",
        summary = "updateAds",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
        }
    )
    @PatchMapping (
        value = "/ads/{id}"
    )
    ResponseEntity<AdsDto> updateAds(
        @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id,
        @Parameter(name = "CreateAds", description = "", required = true) @Valid @RequestBody CreateAdsDto createAds
    );
    /**
     * PATCH /ads/{ad_pk}/comments/{id} : updateComments
     *
     * @param adPk  (required)
     * @param id  (required)
     * @param comment  (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     *         or Forbidden (status code 403)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "updateComments",
        summary = "updateComments",
        tags = { "Объявления" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CommentDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
        }
    )
    @PatchMapping (
        value = "/ads/{ad_pk}/comments/{id}"
    )
    ResponseEntity<CommentDto> updateComments(
        @Parameter(name = "ad_pk", description = "", required = true) @PathVariable("ad_pk") String adPk,
        @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id,
        @Parameter(name = "Comment", description = "", required = true) @Valid @RequestBody CommentDto comment
    );
}
