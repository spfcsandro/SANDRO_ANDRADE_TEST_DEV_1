package backend

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId;
import java.time.format.DateTimeFormatter
import org.apache.commons.math3.stat.descriptive.moment.Variance
import grails.gorm.transactions.Transactional

@Transactional
class StockService {

    def save(Stock stock) {
        stock.validate()
        stock.save()
        stock
    }

    def serviceMethod() {

    }
	
	def getStock(String companyName, int numbersOfHoursUntilNow) {
		ZoneId zoneId = ZoneId.of("America/Maceio");
		Company company = Company.findByName(companyName)
		LocalDateTime dateMinushours = null;
		int hoursBusiness = 8
		int dayConvertHoursBusiness = numbersOfHoursUntilNow / hoursBusiness;
		int remainingHours = numbersOfHoursUntilNow - (dayConvertHoursBusiness * hoursBusiness);
		Duration differentHoursMinusRemainingHours = Duration.between(LocalTime.of(10, 0), LocalTime.of(18, 0)).minusHours(remainingHours);
		dateMinushours = LocalDateTime.of(LocalDate.now().minusDays(dayConvertHoursBusiness), LocalTime.of(18 - remainingHours, 0));
		
		def stocks = Stock.findAllByCompanyAndPriceDateGreaterThanEqual(company, dateMinushours);
		printStocks(company, stocks)

	}

	def void printStocks(Company company, List<Stock> stocks) {
		def startTimeMillis = System.currentTimeMillis()

		println "###########################################################"
		println "                       Begin 						        "
		println "###########################################################"
		println "                                                           "
		println  " Initiation: " + startTimeMillis
		println "                                                           "

		stocks.each { stock ->
			println company.getName()
			println company.getSegment()
			println stock.getPrice()
			println stock.getPriceDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
		}
		println "                                                           "
		println " Finishing: " + startTimeMillis - System.currentTimeMillis()
		println "                                                           "
		println stocks.size() + " quotes retrieved"
		println "                                                           "
		println "###########################################################"
		println "                       The End 						        "
		println "###########################################################"
	}
	
	def double calculateStandardDeviation(double [] prices){
		return Math.sqrt(new Variance().evaluate(prices));
	}
	
	def double calculateSDByCompany(Company company) {
		def stocks = Stock.findAllByCompany(company)
		double[] prices = stocks.stream()
								.mapToDouble({stock -> stock.getPrice()})
								.toArray();
		
		return calculateStandardDeviation(prices);
	}
}
