import { NewCurriculumPageComponent } from './pages/curriculum/new-curriculum-page/new-curriculum-page.component';
import { Routes } from '@angular/router';
import { PageNotFoundComponent } from './pages/errors/page-not-found/page-not-found.component';
import { CurriculumDetailPageComponent } from './pages/curriculum/curriculum-detail-page/curriculum-detail-page.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { LoginComponent } from './pages/auth/login/login.component';

export const routes: Routes = [
  {
    path: '', redirectTo: 'curriculum', pathMatch: 'full'
  },
  {
    path: 'register', component: RegisterComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'curriculum', children: [
      { path: '', redirectTo: 'new', pathMatch: 'full' },
      { path: 'new', component: NewCurriculumPageComponent },
      { path: 'details/:id', component: CurriculumDetailPageComponent }
    ]
  },
  {
    path: '**', component: PageNotFoundComponent
  }
];
