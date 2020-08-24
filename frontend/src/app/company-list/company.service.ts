import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Company } from "./company.model";


@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private url = "http://localhost:8080/companies";

  constructor(private http: HttpClient) { }

  getCompanies(): Observable<Company[]>{
    return this.http.get<Company[]>(this.url);
  }
}