package backend

import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId;

class BootStrap {

   CompanyService companyService
	StockService stockService

	def init = { servletContext ->

		Company company1 = companyService.save(new Company(name: "Nitryx", segment: SegmentEnum.SOFTWARE))
		Company company2 = companyService.save(new Company(name: "Volvo", segment: SegmentEnum.VEHICLES))
		Company company3 = companyService.save(new Company(name: "DELL", segment: SegmentEnum.DRINKS))

		def currentDay = LocalDateTime.now();
        LocalDateTime currentDayMinus30Days = currentDay.minusDays(30);
        while (currentDayMinus30Days.compareTo(currentDay) < 1) {
            def startTime = LocalTime.of(10, 0);
			def endTime = LocalTime.of(18, 0)
         
		    while (startTime.compareTo(endTime) < 1) {
				LocalDateTime dateWithPlusMinutes = LocalDateTime.of(currentDayMinus30Days.date, startTime)
				new Stock(price: new Random().nextDouble(), priceDate: dateWithPlusMinutes, company: company1).save();
				new Stock(price: new Random().nextDouble(), priceDate: dateWithPlusMinutes, company: company2).save();
				new Stock(price: new Random().nextDouble(), priceDate: dateWithPlusMinutes, company: company3).save();
                startTime = startTime.plusMinutes(1);
		   }
            currentDayMinus30Days = currentDayMinus30Days.plusDays(1);
        }

       stockService.getStock("Nitryx", 24)
	}
	def destroy = {
	}
}
