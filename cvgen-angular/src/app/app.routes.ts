import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '', redirectTo: 'curriculum', pathMatch: 'full'
  },
  {
    path: 'curriculum', loadChildren: () => import('./features/curriculum/curriculum.module').then(m => m.CurriculumModule)
  }
];
