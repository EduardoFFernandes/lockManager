package brisa.lockmanager.commons.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
        ElementType.METHOD,
        ElementType.ANNOTATION_TYPE,
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
//@ApiImplicitParams({
//        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
//        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
//        @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query", allowMultiple = true, value = "Sorting criteria in the format: property_name,[asc|desc]. " +
//                "Default sort order is ascending. " +
//                "Multiple sort criteria are supported by 'Add Item Button'.")
//})
public @interface ApiPageable {

    // Nothing to do
}