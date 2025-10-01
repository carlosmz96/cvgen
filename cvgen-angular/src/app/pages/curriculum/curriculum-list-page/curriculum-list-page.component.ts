import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { ConfirmDialog } from 'primeng/confirmdialog';
import { TableModule } from 'primeng/table';
import { ConfirmationService } from 'primeng/api';

import { Curriculum } from '../../../models/Curriculum';
import { CurriculumService } from '../../../services/curriculum/curriculum.service';
import { JwtService } from '../../../services/jwt/jwt.service';
import { LoadingService } from '../../../services/loading/loading.service';
import { ToastService } from '../../../services/toast/toast.service';

@Component({
  selector: 'app-curriculum-list-page',
  imports: [
    TableModule,
    ButtonModule,
    RouterModule,
    ConfirmDialog
  ],
  templateUrl: './curriculum-list-page.component.html',
  styleUrl: './curriculum-list-page.component.scss',
  providers: [ConfirmationService]
})
export class CurriculumListPageComponent {

  curriculums: Curriculum[] = [];

  idToDelete: number = -1;

  constructor(
    private cvService: CurriculumService,
    private jwtService: JwtService,
    private loading: LoadingService,
    private confirmationService: ConfirmationService,
    private toastService: ToastService
  ) { }

  ngOnInit(): void {
    this.getCurriculumsByUserId();
  }

  private getCurriculumsByUserId(): void {
    this.loading.show();
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
    );
  }

  prepareDeleteCurriculum(event: Event, id: number): void {
    this.idToDelete = id;
    this.confirmationService.confirm({
      target: event.target as EventTarget,
      message: '¿Deseas realmente eliminarlo?',
      header: 'Confirmar eliminación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.deleteCurriculum();
      }
    });
  }

  deleteCurriculum(): void {
    this.cvService.deleteCurriculum(this.idToDelete).subscribe(
      {
        next: () => {
          this.getCurriculumsByUserId();
        },
        error: err => {
          console.error('Error al intentar eliminar el currículum: ', err);
        },
        complete: () => this.toastService.show('success', 'Confirmación', 'Currículum eliminado')
      }
    );
  }

}
