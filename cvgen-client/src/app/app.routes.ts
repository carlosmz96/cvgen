import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'curriculum',
    loadChildren: () => import('./components/curriculum/curriculum.module').then(m => m.CurriculumModule)
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
  }
];
