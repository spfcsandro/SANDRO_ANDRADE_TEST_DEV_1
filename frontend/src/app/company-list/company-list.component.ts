import { Component, OnInit } from '@angular/core';
import { CompanyService } from "./company.service";
import { Company } from './company.model';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.scss']
})
export class CompanyListComponent implements OnInit {

  companies: Company[];

  constructor(private companyService: CompanyService) { }

  ngOnInit(): void {
  }

  getCompanies(){
    this.companyService.getCompanies()
      .subscribe(res => this.companies = res)
  }
}
