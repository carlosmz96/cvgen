import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Curriculum } from '../../models/Curriculum';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class CurriculumService {

  cvUrl: string = environment.apiBaseUrl + '/curriculums';
  token: string = '';
  headers: HttpHeaders;

  constructor(
    private httpClient: HttpClient,
    private cookieService: CookieService
  ) {
    this.token = this.cookieService.get('token');
    this.headers = new HttpHeaders({ 'Authorization': `Bearer ${this.token}`, 'Content-Type': 'application/json' })
  }

  getCurriculum(id: number): Observable<Curriculum> {
    return this.httpClient.get<Curriculum>(this.cvUrl + `/${id}`, { headers: this.headers });
  }

  createCurriculum(cv: Curriculum): Observable<Curriculum> {
    return this.httpClient.post<Curriculum>(this.cvUrl, cv, { headers: this.headers });
  }

  updateCurriculum(cv: Curriculum, id: number): Observable<Curriculum> {
    return this.httpClient.put<Curriculum>(this.cvUrl + `/${id}`, cv, { headers: this.headers });
  }

  deleteCurriculum(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.cvUrl + `${id}`, { headers: this.headers });
  }

  downloadCurriculum(id: number, template: string): Observable<Blob> {
    return this.httpClient.get(this.cvUrl + `/${id}/pdf?template=${template}`, {
      responseType: 'blob', headers: this.headers
    });
  }

}
