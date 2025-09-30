import { NewCurriculumPageComponent } from './pages/curriculum/new-curriculum-page/new-curriculum-page.component';
import { Routes } from '@angular/router';
import { PageNotFoundComponent } from './pages/errors/page-not-found/page-not-found.component';
import { CurriculumDetailPageComponent } from './pages/curriculum/curriculum-detail-page/curriculum-detail-page.component';
import { RegisterComponent } from './pages/auth/register-page/register-page.component';
import { LoginComponent } from './pages/auth/login-page/login-page.component';
import { authGuard } from './guards/auth.guard';
import { HomeComponent } from './pages/home/home.component';
import { CurriculumListPageComponent } from './pages/curriculum/curriculum-list-page/curriculum-list-page.component';

export const routes: Routes = [
  {
    path: '', redirectTo: 'home', pathMatch: 'full'
  },
  {
    path: 'register', component: RegisterComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'home', component: HomeComponent, canActivate: [authGuard]
  },
  {
    path: 'curriculums', children: [
      { path: '', component: CurriculumListPageComponent },
      { path: 'new', component: NewCurriculumPageComponent },
      { path: 'details/:id', component: CurriculumDetailPageComponent }
    ],
    canActivate: [authGuard]
  },
  {
    path: '**', component: PageNotFoundComponent
  }
];
