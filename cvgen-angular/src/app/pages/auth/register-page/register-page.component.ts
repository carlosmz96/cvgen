import { LoadingService } from '../../../services/loading/loading.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';

import { AuthService } from '../../../services/auth/auth.service';
import { UserRegister } from '../../../models/UserRegister';
import { UserResponse } from '../../../models/UserResponse';

@Component({
  selector: 'app-register',
  imports: [
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    MessageModule,
    CommonModule,
    RouterModule
  ],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss'
})
export class RegisterComponent {

  registerForm: FormGroup;

  userRegister: UserRegister = {
    fullName: '',
    email: '',
    username: '',
    password: ''
  }

  pswdNotEqual: boolean = false;
  pswdNotEqualMsg: string = 'El valor de la contraseña no coincide.';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private loading: LoadingService
  ) {
    this.registerForm = this.fb.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      passwordRepeated: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    this.loading.show('Cargando...');
    this.pswdNotEqual = false;
    if (this.registerForm.valid && this.registerForm.get('password')?.value == this.registerForm.get('passwordRepeated')?.value) {
      const { fullName, email, username, password } = this.registerForm.value;
      this.userRegister = { fullName, email, username, password };
      this.authService.register(this.userRegister).subscribe(
        {
          next: (data: UserResponse) => {
            this.loading.hide();
            this.router.navigate(['login']);
          },
          error: err => {
            this.loading.hide();
            console.error('Error al registrar de usuario:', err);
          }
        }
      );
    } else {
      this.loading.hide();
      this.registerForm.markAllAsTouched();
      this.pswdNotEqual = true;
      console.error(this.pswdNotEqualMsg);
    }
  }

  isInvalid(field: string): boolean {
    const fieldToCheck = this.registerForm.get(field);
    return fieldToCheck?.invalid! && fieldToCheck.touched;
  }

}
