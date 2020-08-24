package backend

import java.util.stream.Collectors

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("companies")
class CompanyController {

   static responseFormats = ['json', 'xml']
	
   CompanyService companyService
   StockService stockService
	
   @GetMapping
   def index() { 
	   def companies = companyService.listAll()
							.stream()
							.map({company -> 
						        CompanyDTO companyDTO = new CompanyDTO();
						        companyDTO.setName(company.getName());
						        companyDTO.setSegment(company.getSegment().name());
						        companyDTO.setStandardDeviation(stockService.calculateSDByCompany(company));
						        return companyDTO;
						    }).collect(Collectors.toList());
	
		respond companies
	}
}
