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

  constructor(
    private httpClient: HttpClient,
    private cookieService: CookieService
  ) {
    this.token = this.cookieService.get('token');
  }

  getCurriculum(id: number): Observable<Curriculum> {
    return this.httpClient.get<Curriculum>(this.cvUrl + `/${id}`, {
      headers: {
        'Authorization': `Bearer ${this.token}`
      }
    });
  }

  createCurriculum(cv: Curriculum): Observable<Curriculum> {
    return this.httpClient.post<Curriculum>(this.cvUrl, cv, {
      headers: {
        'Authorization': `Bearer ${this.token}`
      }
    });
  }

  updateCurriculum(cv: Curriculum, id: number): Observable<Curriculum> {
    return this.httpClient.put<Curriculum>(this.cvUrl + `/${id}`, cv, {
      headers: {
        'Authorization': `Bearer ${this.token}`
      }
    });
  }

  deleteCurriculum(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.cvUrl + `${id}`, {
      headers: {
        'Authorization': `Bearer ${this.token}`
      }
    });
  }

  downloadCurriculum(id: number, template: string): Observable<Blob> {
    return this.httpClient.get(this.cvUrl + `/${id}/pdf?template=${template}`, {
      responseType: 'blob',
      headers: {
        'Authorization': `Bearer ${this.token}`
      }
    });
  }

}
