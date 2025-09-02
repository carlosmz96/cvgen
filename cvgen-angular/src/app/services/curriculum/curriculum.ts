import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Curriculum {

  cvUrl: string = environment.apiBaseUrl + '/curriculums';

  constructor(
    private httpClient: HttpClient
  ) { }

  getCurriculum(id: number): Observable<Curriculum> {
    return this.httpClient.get<Curriculum>(this.cvUrl + `/${id}`);
  }

  createCurriculum(cv: Curriculum): Observable<Curriculum> {
    // const headers = new HttpHeaders({'': ''})
    return this.httpClient.post<Curriculum>(this.cvUrl, { cv });
  }

  updateCurriculum(cv: Curriculum, id: number): Observable<Curriculum> {
    return this.httpClient.put<Curriculum>(this.cvUrl + `/${id}`, { cv });
  }

  deleteCurriculum(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.cvUrl + `${id}`);
  }

}
