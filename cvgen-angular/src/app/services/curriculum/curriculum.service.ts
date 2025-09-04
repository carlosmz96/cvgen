import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Curriculum } from '../../models/Curriculum';

@Injectable({
  providedIn: 'root'
})
export class CurriculumService {

  cvUrl: string = environment.apiBaseUrl + '/curriculums';

  constructor(
    private httpClient: HttpClient
  ) { }

  getCurriculum(id: number): Observable<Curriculum> {
    return this.httpClient.get<Curriculum>(this.cvUrl + `/${id}`);
  }

  createCurriculum(cv: Curriculum): Observable<Curriculum> {
    return this.httpClient.post<Curriculum>(this.cvUrl, { cv });
  }

  updateCurriculum(cv: Curriculum, id: number): Observable<Curriculum> {
    return this.httpClient.put<Curriculum>(this.cvUrl + `/${id}`, { cv });
  }

  deleteCurriculum(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.cvUrl + `${id}`);
  }

}
