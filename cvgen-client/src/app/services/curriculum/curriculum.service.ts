import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Curriculum } from '../../models/Curriculum';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class CurriculumService {

  private api = `${environment.apiUrl}/curriculums`;

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) {}

  listarPorUsuario(id: number): Observable<Curriculum[]> {
    const headers: HttpHeaders = new HttpHeaders({'Authorization': 'Bearer ' + this.auth.token()})
    return this.http.get<Curriculum[]>(`${this.api}/${id}`, { headers: headers });
  }

}
