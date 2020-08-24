package backend

import grails.gorm.transactions.Transactional

@Transactional
class CompanyService {

    def save(Company company) {
        log.info 'validate Company'
        company.validate()
        log.info 'save Company'
        company.save()
        company
    }

    def listAll(){
        def companys = Company.list()
    }
}
