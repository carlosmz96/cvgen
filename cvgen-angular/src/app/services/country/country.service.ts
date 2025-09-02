import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  countryApi: string = 'https://restcountries.com/v3.1/all?fields=translations';

  constructor(
    private httpClient: HttpClient
  ) { }

  getCountries(): Observable<any> {
    return this.httpClient.get<any>(this.countryApi);
  }

}
