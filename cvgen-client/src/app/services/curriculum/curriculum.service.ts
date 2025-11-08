import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';
import { Curriculum } from '../../models/Curriculum';

@Injectable({
  providedIn: 'root'
})
export class CurriculumService {

  private api = `${environment.apiUrl}/curriculums`;

  constructor(private http: HttpClient) {}

  listarPorUsuario(id: number): Observable<Curriculum[]> {
    return this.http.get<Curriculum[]>(`${this.api}/${id}`);
  }

  crearCurriculum(curriculum: Curriculum): Observable<Curriculum> {
    return this.http.post<Curriculum>(`${this.api}/crear`, curriculum);
  }

  generarPdf(curriculum: Curriculum): Observable<Blob> {
    return this.http.post(`${this.api}/generar-pdf`, curriculum, { responseType: 'blob' });
  }

  editarCurriculum(curriculum: Curriculum): Observable<Curriculum> {
    return this.http.put<Curriculum>(`${this.api}/editar/${curriculum.id}`, curriculum);
  }

  eliminarCurriculum(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/eliminar/${id}`);
  }

}
