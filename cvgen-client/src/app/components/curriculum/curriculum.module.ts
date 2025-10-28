import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuard } from '../../guards/auth.guard';
import { CrearCurriculumComponent } from './crear-curriculum/crear-curriculum.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'crear',
    pathMatch: 'full'
  },
  {
    path: 'crear',
    component: CrearCurriculumComponent,
    canActivate: [authGuard]
  }
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ]
})
export class CurriculumModule { }
