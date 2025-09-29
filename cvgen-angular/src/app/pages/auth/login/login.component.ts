import { LoadingService } from './../../../services/loading/loading.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { InputTextModule } from "primeng/inputtext";
import { MessageModule } from 'primeng/message';

import { AuthService } from '../../../services/auth/auth.service';
import { UserLogin } from '../../../models/UserLogin';
import { UserResponse } from '../../../models/UserResponse';

import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  imports: [
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    MessageModule,
    CommonModule,
    RouterModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm: FormGroup;

  userLogin: UserLogin = {
    username: '',
    password: ''
  }

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private cookieService: CookieService,
    private loading: LoadingService
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    this.loading.show('Cargando...');
    if (this.loginForm.valid) {
      this.userLogin = this.loginForm.value;
      this.authService.login(this.userLogin).subscribe(
        {
          next: (data: any) => {
            console.log('Sesión iniciada con éxito:', data);
            this.cookieService.set('token', data.token);
            this.router.navigate(['/']);
          },
          error: err => {
            this.loading.hide();
            console.error('Error al iniciar sesión:', err);
          }
        }
      );
    } else {
      this.loading.hide();
      this.loginForm.markAllAsTouched();
    }
  }

  isInvalid(field: string): boolean {
    const fieldToCheck = this.loginForm.get(field);
    return fieldToCheck?.invalid! && fieldToCheck.touched;
  }

}
