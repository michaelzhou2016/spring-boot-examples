package ai.guiji.swagger.config;

/**
 * author: zhouliliang
 * Date: 2019/11/7 15:27
 * Description: Swagger2配置
 */

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {

    @Bean
    public UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder.builder().supportedSubmitMethods(new String[]{})
                .displayOperationId(true)
                .build();
    }

    @Bean(value = "defaultApi")
    @Order(value = 4)
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ai.guiji.swagger"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters())
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()));
    }

    /**
     * 额外添加的请求头信息
     *
     * @return
     */
    private List<Parameter> parameters() {
        Parameter parameter1 = new ParameterBuilder()
                .name("token").description("token令牌").modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false).build();

        return Collections.singletonList(parameter1);
    }

    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui很棒~~~！！！")
                .description("<div style='font-size:14px;color:red;'>swagger-bootstrap-ui-demo RESTful APIs</div>")
                .termsOfServiceUrl("http://www.group.com/")
                .contact(new Contact("zhouliliang", "", "strength_zhou@qq.com"))
                .version("1.0")
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui RESTful APIs")
                .description("swagger-bootstrap-ui")
                .termsOfServiceUrl("http://localhost:8888/")
                .contact(new Contact("zhouliliang", "", "strength_zhou@qq.com"))
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
    }
}