package pojo;
/*
@author Bhimashankar Teli
 */
import java.util.Map;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode
@Builder

public class HttpMethodParameter {
    private Map<String, ?> queryParams;
    private Map<String, ?> formParams;
    private Map<String, Object> pathParams;
    private String bodyParams;
    private  Map<String, String> headerParams;
}
