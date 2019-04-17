package com.sdrc.mongo.services;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.sdrc.mongo.domains.Area;
import com.sdrc.mongo.domains.Employee;
import com.sdrc.mongo.repository.AreaRepo;
import com.sdrc.mongo.repository.EmployeeRepo;
import com.sdrc.mongo.services.dataFetcher.AllEmployeesDataFetcher;
import com.sdrc.mongo.services.dataFetcher.EmployeeDataFetcher;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class EmployeeGraphQlServiceImpl implements  EmployeeGraphQlServices{

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	AreaRepo areaRepo;
	
	@Autowired
	AllEmployeesDataFetcher allEmployeesDataFetcher;
	
	@Autowired
	EmployeeDataFetcher employeeDataFetcher;
	
	@Value("classpath:Employee.graphql")
	//@Value("classpath:graphql/Authority.graphqls")
	Resource resource;
	
	 private GraphQL graphQL;
	
//	@Override
//	public void GraphQlEmp() {
//		
//	
//	}
	
	// load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {


        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        
        //getting schima by URL
        
//        URL url = Resources.getResource("schema.graphqls");
//        String sdl = Resources.toString(url, Charsets.UTF_8);
//        GraphQLSchema schema = buildSchema(sdl);
        
        graphQL = GraphQL.newGraphQL(schema).build();
    }
    
    private RuntimeWiring buildRuntimeWiring() {
    	DataFetcher<List<Employee>> fetcher1 = data -> {
			return (List<Employee>) employeeRepo.findAll();
		};

		DataFetcher<Employee> fetcher2 = data -> {
			return employeeRepo.findByUserName(data.getArgument("userName"));
		};
    	
		DataFetcher<List<Area>> fetcher3 = data -> {
			return (List<Area>) areaRepo.findAll();
		};

		DataFetcher<Area> fetcher4 = data -> {
			return areaRepo.findByAreaId(data.getArgument("areaId"));
		};
    	
//        return RuntimeWiring.newRuntimeWiring()
//                .type("Query", typeWiring -> typeWiring
//                        .dataFetcher("allEmployee", allEmployeesDataFetcher)
//                        .dataFetcher("employee", employeeDataFetcher))
//                .build();
		
		return RuntimeWiring.newRuntimeWiring().type("Query",
				typeWriting -> typeWriting
				.dataFetcher("allEmployee", fetcher1)
				.dataFetcher("employee", fetcher2)
				.dataFetcher("allArea", fetcher3)
				.dataFetcher("area", fetcher4))
				.build();
    }

    

    public GraphQL getGraphQL() {
        return graphQL;
    }

}
