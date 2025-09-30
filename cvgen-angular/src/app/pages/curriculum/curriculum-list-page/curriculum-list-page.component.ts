import { Component } from '@angular/core';

import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { RouterModule } from '@angular/router';

import { Curriculum } from '../../../models/Curriculum';
import { CurriculumService } from '../../../services/curriculum/curriculum.service';
import { JwtService } from '../../../services/jwt/jwt.service';
import { LoadingService } from '../../../services/loading/loading.service';

@Component({
  selector: 'app-curriculum-list-page',
  imports: [
    TableModule,
    ButtonModule,
    RouterModule
],
  templateUrl: './curriculum-list-page.component.html',
  styleUrl: './curriculum-list-page.component.scss'
})
export class CurriculumListPageComponent {

  curriculums: Curriculum[] = [];

  constructor(
    private cvService: CurriculumService,
    private jwtService: JwtService,
    private loading: LoadingService
  ) { }

  ngOnInit(): void {
    this.loading.show();
    this.getCurriculumsByUserId();
  }

  private getCurriculumsByUserId(): void {
    this.cvService.getCurriculumsByUser(this.jwtService.getUserId() as number).subscribe(
      {
        next: (data: any) => {
          this.curriculums = data.content as Curriculum[];
          this.loading.hide();
        },
        error: err => {
          this.loading.hide();
          console.error('Error al obtener los currículums del usuario: ', err);
        }
      }
    )
  }

}
