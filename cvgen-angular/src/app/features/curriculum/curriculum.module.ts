import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CurriculumDetailComponent } from './pages/curriculum-detail/curriculum-detail.component';
import { NewCurriculumComponent } from './pages/new-curriculum/new-curriculum.component';

const routes: Routes = [
  {
    path: '', redirectTo: 'new', pathMatch: 'full'
  },
  { path: 'new', component: NewCurriculumComponent },
  { path: 'detail/:id', component: CurriculumDetailComponent }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class CurriculumModule { }
