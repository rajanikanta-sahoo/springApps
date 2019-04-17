package com.sdrc.mongo.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdrc.mongo.services.GraphQlService;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;

@Controller
public class GraphQlController {

//	@Autowired
//	GraphQlService graphQlService;
	
	private final GraphQL graphQL;
	
	 public GraphQlController(GraphQlService graphQlService) {
	        GraphQLSchema schema = new GraphQLSchemaGenerator()
	                .withResolverBuilders(
	                        //Resolve by annotations
	                        new AnnotatedResolverBuilder())
	                .withOperationsFromSingleton(graphQlService)
	                .withValueMapperFactory(new JacksonValueMapperFactory())
	                .generate();
	        graphQL = GraphQL.newGraphQL(schema).build();
	    }
	 @PostMapping(value = "/getEmps")
	 @ResponseBody
	    public Map<String, Object> graphql(@RequestBody Map<String, String> request, HttpServletRequest raw) {
	     System.out.println("hi1");  
		 ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
	                .query(request.get("query"))
	                .operationName(request.get("operationName"))
	                .context(raw)
	                .build());
		 System.out.println("hi2");
	        return executionResult.toSpecification();
	    }
	
//	@RequestMapping("/getEmps")
//	@ResponseBody
//	public List<Employee> getEmps(@RequestBody String query){
//		
//		return graphQlService.getEmployees();
//	}
}
